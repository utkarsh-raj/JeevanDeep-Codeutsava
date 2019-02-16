from django.urls import path
from . import views

urlpatterns = [
    path('user_login', views.user_login, name = "user_login"),
    path('user_signup', views.user_signup, name = "user_signup"),
    path('user_signup/otp', views.user_signupOTPHandling, name = "user_signupOTPHandling"),
    path('user_signup/otp/resend', views.user_resendOTP, name = "user_resendOTPHandling"),
    path('user_signup/complete', views.user_signupComplete, name="user_signupComplete"),
    path('bank_signup', views.bank_signup, name="bank_signup"),
]