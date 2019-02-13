from django.shortcuts import render
from django.http import JsonResponse
from ask_feed.models import Ranger, Ask, Answer
import jwt

# Create your views here.

def encodeJWT(data, secret):
    return jwt.encode(data, secret)

def decodeJWT(data, secret):
    return jwt.decode(data, secret)

def ask_feed(request):
    asks = Ask.objects.all()

    askList = []

    for ask in asks:
        askList.append(ask)
    
    response = {
        "token": "authentication token",
        "askList": askList
    }
    return JsonResponse(response)

def new_ask(request):
    if request.method == "POST":
        token = request.META["HTTP_AUTHORISATION"].split()
        token = token[1]

        auth = decodeJWT(token, "Secret Keyword")
        content = request.POST["content"]

        ranger_instance = Ranger.objects.get(id = int(auth["userId"]))

        ask = Ask(ranger = ranger_instance, content = content, cheers = 0)
        ask.save()