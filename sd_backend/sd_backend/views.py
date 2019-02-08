from django.http import JsonResponse

def splash(request):
    return JsonResponse({
        "status": "success",
        "token": "authorization token"
    })