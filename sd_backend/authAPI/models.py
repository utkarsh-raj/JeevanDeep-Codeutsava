from django.db import models

# Create your models here.

class User(models.Model):
    phoneNumber = models.CharField(max_length = 10, help_text = "The User Phone Number", default = 0)
    password = models.CharField(max_length = 64, help_text = "The User Password")

    def __str__(self):
        return self.phoneNumber

class Verification(models.Model):
    otp = models.CharField(max_length = 4, help_text = "The unique OTP of user", default = 0)
    user = models.ForeignKey(User, on_delete = models.CASCADE, default = 0)
    numberOfTries = models.CharField(max_length = 1, help_text = "The number of times the user has requested the OTP", default = 0)

    def __str__(self):
        return self.otp