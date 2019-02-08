from django.shortcuts import render
from django.http import JsonResponse
from ask_feed.models import Ask

# Create your views here.

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