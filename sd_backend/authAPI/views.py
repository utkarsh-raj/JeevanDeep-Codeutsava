from django.shortcuts import render, redirect
from authAPI.models import User, Verification
from django.http import JsonResponse
import jwt
from hashlib import sha256
from urllib.parse import urlencode
from random import randint
import http.client
import json

# Create your views here.

# -------Helper functions for encrypting, encoding and decoding-------

def encrypt(string, secret):
    """
        A function to encrypt a given string with the secret string and the SHA256 Hashing Algorithm.
        
        Takes the query string and the secret string as the input.
        Returns the hash string.
    """
    string += secret
    hashstring = sha256(string.encode()).hexdigest()
    return hashstring

"""
    Wrapper functions to encode and decode the JWT.

    Takes the input as the data payload and the secret string to verify the token signature.
"""
def encodeJWT(data, secret):
    return jwt.encode(data, secret)

def decodeJWT(data, secret):
    return jwt.decode(data, secret)

# -------Helper functions completed-------

"""
    Method to handle the login endpoint.
"""

def login(request):
    """
    Method to handle the login endpoint.
    """

    # Extraction of the form data
    if request.method == "POST":
        phoneNumber = request.POST["phoneNumber"]
        password = request.POST["password"]

        # Generate the encrypted password for saving in the DB
        hashString = encrypt(password, "Secret Keyword")

        try:

            # Try and get user details from the DB
            user = User.objects.get(phoneNumber = phoneNumber, password = hashString)

            # Placeholder Header Data for the user to encode in the JWT
            headerData = {
                "accessRights": "global",
                "userId": user.id
            }
            
            # Encoding the token
            token = encodeJWT(headerData, "Secret Keyword")

            # Building response payload for the response
            response = {
                "success": "True",
                "message": "Login Successful",
                "token": str(token)[2 : -1]
            }

            return JsonResponse(response)
        except:
            # Possible redirect to signup page
            return JsonResponse({
                "loginError": "The user is not signed up yet!"
            })

def signup(request):
    """
        Method to handle signup endpoint
    """
    if request.method == "POST":
        # If the request is POST, extract the form data
        phoneNumber = request.POST["phoneNumber"]
        password = request.POST["password"]

        # OTP generation
        # We can deploy state algorithms to generate a better OTP if the need be.
        otp = "".join(str(x) for x in [randint(0, 9), randint(0, 9), randint(0, 9), randint(0, 9)])
        
        # Calling the SMS Service provider API and decoding the information
        # The request is a GET Request to a server, and should be converted to POST requests in next builds
        conn = http.client.HTTPConnection("control.msg91.com")
        conn.request("GET", "/api/sendhttp.php?country=91&sender=CodeNi&route=4&mobiles=" + phoneNumber + "&authkey=243883A5rE7Q42GvZv5bcd771c&message=The%20verification%20OTP%20is%20" + otp)
        res = conn.getresponse()
        data = res.read()
        data = data.decode("utf-8")

        if len(data) == 24:
            # If the hash returned from the API is of length 24, the message is sent

            # Initialize new User Object and Verification Object
            user = User(phoneNumber = phoneNumber, password = password)
            user.save()
            verification = Verification(otp = otp, user = user, numberOfTries = 1)
            verification.save()

            # Make the token payload
            authData = {
                "userId": user.id,
                "verificationId": verification.id,
                "phoneNumber": user.phoneNumber
            }

            # Encoding the token
            token = encodeJWT(authData, "Secret Keyword")

            return JsonResponse({
                "success": "True",
                "message": "redirect to endpoint for the otp",
                "token": str(token)[2 : -1]
            })

def signupOTPHandling(request):
    """
        Method to handle the OTP endpoint
        The Authorization header is required
    """
    # Extract the token
    token = request.META["HTTP_AUTHORIZATION"].split()
    token = token[1]
    # Extraction completed

    # Decode the token to get the payload
    authData = decodeJWT(token, "Secret Keyword")

    # Get the corresponding user and the Verification Object from the DB which are required for verification
    verificationObject = Verification.objects.get(id = authData["verificationId"])
    user = User.objects.get(id = authData["userId"])

    # To check if the user has not automated the tries
    if verificationObject.numberOfTries > "3":
        return JsonResponse({
            "status": "failure",
            "message": "Number of tries for the user is exhausted"
        })
    verificationObject.numberOfTries = str(int(verificationObject.numberOfTries) + 1)
    verificationObject.save()

    # Get the form data
    otp = request.POST["otp"]

    if otp == verificationObject.otp:
        # Make the token payload
        authData = {
            "userId": user.id,
            "accessRights": "global"
        }

        # Encoding the token
        token = encodeJWT(authData, "Secret Keyword")
        return JsonResponse({
            "success": "True",
            "message": "redirect to the endpoint for completing the profile"
        })
    else:
        return JsonResponse({
            "status": "failure",
            "message": "The otp is not corect"
        })

def resendOTP(request):
    # Extract the token
    token = request.META["HTTP_AUTHORIZATION"].split()
    token = token[1]
    # Extraction completed

    authData = decodeJWT(token, "Secret Keyword")

    user = User.objects.get(id = authData["userId"])
    verificationObject = Verification.objects.get(id = authData["verificationId"])
    phoneNumber = user.phoneNumber

    # To check if the user has not automated the tries
    if verificationObject.numberOfTries > "3":
        return JsonResponse({
            "status": "failure",
            "message": "Number of tries for the user is exhausted"
        })

    otp = "".join(str(x) for x in [randint(0, 9), randint(0, 9), randint(0, 9), randint(0, 9)])
    
    # Calling the SMS Service provider API and decoding the information
    conn = http.client.HTTPConnection("control.msg91.com")
    conn.request("GET", "/api/sendhttp.php?country=91&sender=CodeNi&route=4&mobiles=" + phoneNumber + "&authkey=243883A5rE7Q42GvZv5bcd771c&message=The%20verification%20OTP%20is%20" + otp)
    res = conn.getresponse()
    data = res.read()
    data = data.decode("utf-8")

    # The DB record of the OTP is updated
    verificationObject.otp = otp
    verificationObject.numberOfTries = str(int(verificationObject.numberOfTries) + 1)
    verificationObject.save()

    return JsonResponse({
        "status": "success",
        "message": "redirect to the enter OTP endpoint"
    })