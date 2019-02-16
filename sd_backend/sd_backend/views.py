from django.http import JsonResponse
import firebase_admin
from firebase_admin import messaging
from firebase_admin import credentials
from authAPI.models import User, Verification, Blood_Bank, Donation, Blood_Unit, Campaign
import jwt
firebase_admin.initialize_app(credentials.Certificate("/home/utkarsh/Downloads/sabkamalikek-36e1c-firebase-adminsdk-6n3ol-5bf162ba94.json"))

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

def splash(request):
    return JsonResponse({
        "status": "success",
        "token": "authorization token"
    })

def fcm(request):
    if request.method == "POST":
        print(request.POST)
        access_token = request.POST["access_token"]
        fcm = request.POST["fcm"]

        authData = decodeJWT(access_token, "Secret Keyword")

        user_instance = User.objects.get(id = authData["userId"])
        user_instance.fcm = fcm
        user_instance.save()

        # message = messaging.Message(
        #     data={
        #         "message": "Hello World"
        #     },
        #     token=fcm,
        # )

        # response = messaging.send(message)

        # print("Response sent", response)
        return JsonResponse({
            "success": "true"
        })
    else:
        return JsonResponse({
            "message": "success"
        })

def req(request):
    if request.method == "POST":
        access_token = request.POST["access_token"]

        authData = decodeJWT(access_token, "Secret Keyword")
        print(authData)

        user_instance = User.objects.get(id = authData["userId"])
        
        donors = User.objects.all()

        donor_list = []
        for donor in donors:
            donor_list.append({
                "id": donor.id,
                "blood_group": donor.blood_group,
                "name": donor.name,
                "phoneNumber": donor.phoneNumber,
                "location": donor.location,
                "latitude": donor.latitude,
                "longitude": donor.longitude,
                "reputation": donor.reputation,
                "verified": donor.verified
            })
        response = {
            "status": "success",
            "access_token": access_token,
            "donor_list": donor_list
        }

        return JsonResponse(response)

def bank_req(request):
    if request.method == "POST":
        access_token = request.POST["access_token"]

        authData = decodeJWT(access_token, "Secret Keyword")
        print(authData)

        blood_bank_instance = Blood_Bank.objects.get(id = authData["bankId"])
        
        banks = Blood_Bank.objects.all()

        bank_list = []
        for bank in banks:
            bank_list.append({
                "id": bank.id,
                "phoneNumber": bank.phoneNumber,
                "name": bank.name,
                "location": bank.location,
                "image": bank.image,
                "latitude": bank.latitude,
                "longitude": bank.longitude
            })
        response = {
            "status": "success",
            "access_token": access_token,
            "bank_list": bank_list
        }

        return JsonResponse(response)

def request_blood(request):

    # If no fcm is being sent retrieve from the DB.
    access_token = request.POST["access_token"]
    fcm = request.POST["fcm"]
    
    if request.POST == "POST":
        name = request.POST["name"]
        age = request.POST["age"]
        phoneNumber = request.POST["phoneNumber"]
        treatment = request.POST["treatment"]
        number_of_units = request.POST["num_of_units"]
        blood_group = request.POST["blood_group"]
        date = request.POST["date"]

        donors = User.objects.all()

        for donor in donors:
            if fcm != donor.fcm:
                message = messaging.Message(data = {
                    "name": name,
                    "age": age,
                    "phoneNumber": phoneNumber,
                    "treatment": treatment,
                    "number_of_units": number_of_units,
                    "blood_group": blood_group,
                    "date": date
                }, token = donor.fcm)

                response = messaging.send(message)

        print("Response sent", response)
        return JsonResponse({
            "success": "true",
            "access_token": access_token
        })

def bank_create(request):
    if request.method == "POST":
        access_token = request.POST["access_token"]
        name = request.POST["name"]
        location = request.POST["location"]
        state = request.POST["state"]
        description = request.POST["description"]

        authData = decodeJWT(access_token, "Secret Keyword")
        print(authData)

        blood_bank_instance = Blood_Bank.objects.get(id = authData["bankId"])

        campaign = Campaign(name = name, blood_bank = blood_bank_instance, location = location, state = state, description = description)
        campaign.save()
        response = {
            "status": "success",
            "access_token": access_token,
        }

        return JsonResponse(response)

def add_donation(request):
    access_token = request.POST["access_token"]

    authData = decodeJWT(access_token, "Secret Keyword")

    user_instance = User.objects.get(id = authData["userId"])

    donation = Donation(user = user_instance)
    user_instance.verified = "yes"
    user_instance.reputation = str(int(user_instance.reputation) + 1)

    user_instance.save()
    donation.save()

    response = {
        "status": "success",
        "access_token": access_token,
    }

    return JsonResponse(response)

def campaign_list(request):
    if request.method == "POST":
        access_token = request.POST["access_token"]

        authData = decodeJWT(access_token, "Secret Keyword")
        print(authData)

        # blood_bank_instance = Blood_Bank.objects.get(id = authData["bankId"])
        
        campaigns = Campaign.objects.all()

        campaign_list = []
        for campaign in campaigns:
            campaign_list.append({
                "id": campaign.id,
                "organiser": campaign.blood_bank.name,
                "name": campaign.name,
                "location": campaign.location,
                "image": campaign.image,
                "latitude": campaign.latitude,
                "longitude": campaign.longitude,
                "description": campaign.description,
                "date": campaign.date,
                "phoneNumber": campaign.blood_bank.phoneNumber
            })
        print(campaign_list)
        response = {
            "status": "success",
            "access_token": access_token,
            "campaign_list": campaign_list
        }

        return JsonResponse(response)

def bank_update(request):
    if request.method == "POST":
        access_token = request.POST["access_token"]

        a_pos = request.POST["a_pos"]
        a_pos_num = request.POST["a_pos_num"]
        a_neg = request.POST["a_neg"]
        a_neg_num = request.POST["a_neg_num"]
        b_pos = request.POST["b_pos"]
        b_pos_num = request.POST["b_pos_num"]
        b_neg = request.POST["b_neg"]
        b_neg_num = request.POST["b_neg_num"]
        o_pos = request.POST["o_pos"]
        o_pos_num = request.POST["o_pos_num"]
        o_neg = request.POST["o_neg"]
        o_neg_num = request.POST["o_neg_num"]
        ab_pos = request.POST["ab_pos"]
        ab_pos_num = request.POST["ab_pos_num"]
        ab_neg = request.POST["ab_neg"]
        ab_neg_num = request.POST["ab_neg_num"]

        authData = decodeJWT(access_token, "Secret Keyword")
        print(authData)

        bank = Blood_Bank.objects.get(id = authData["bankId"])

        for unit in range(0, a_pos_num):
            blood = Blood_Unit(blood_group = "a_pos", blood_bank = bank)
            blood.save()
        for unit in range(0, a_neg_num):
            blood = Blood_Unit(blood_group = "a_neg", blood_bank = bank)
            blood.save()
        for unit in range(0, b_pos_num):
            blood = Blood_Unit(blood_group = "b_pos", blood_bank = bank)
            blood.save()
        for unit in range(0, b_neg_num):
            blood = Blood_Unit(blood_group = "b_neg", blood_bank = bank)
            blood.save()
        for unit in range(0, o_pos_num):
            blood = Blood_Unit(blood_group = "o_pos", blood_bank = bank)
            blood.save()
        for unit in range(0, o_neg_num):
            blood = Blood_Unit(blood_group = "o_neg", blood_bank = bank)
            blood.save()
        for unit in range(0, ab_pos_num):
            blood = Blood_Unit(blood_group = "ab_pos", blood_bank = bank)
            blood.save()
        for unit in range(0, ab_neg_num):
            blood = Blood_Unit(blood_group = "ab_neg", blood_bank = bank)
            blood.save()
        return JsonResponse({
            "success": "true",
            "message": "The inventory is now updated."
        })