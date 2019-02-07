from django.urls import path
from . import views

urlpatterns = [
    path('login', views.login, name = "login"),
    path('signup', views.signup, name = "signup"),
    path('signup/otp', views.signupOTPHandling, name = "signupOTPHandling"),
    path('signup/otp/resend', views.resendOTP, name = "resendOTPHandling"),
]