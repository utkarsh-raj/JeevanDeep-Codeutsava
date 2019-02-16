from django.db import models
import datetime

# Create your models here.

class User(models.Model):
    phoneNumber = models.CharField(max_length = 10, help_text = "The User Phone Number", default = 0)
    name = models.CharField(max_length = 500, help_text = "The Name of the User", default = 0)
    blood_group = models.CharField(max_length = 5, help_text = "The Blood Group of the User", default = 0)
    location = models.CharField(max_length = 500, help_text = "The Location of the User", default = 0)
    state = models.CharField(max_length = 500, help_text = "The state of the User", default = 0)
    fcm = models.CharField(max_length = 1000, help_text = "The FCM Registration Token for the user", default = 0)
    verified = models.CharField(max_length = 5, help_text = "The verification status of the donor", default = 0)
    reputation = models.CharField(max_length = 100, help_text = "The Reputation Point earned by the user", default = 0)
    latitude = models.CharField(max_length = 100, help_text = "The Reputation Point earnedby the user", default = 0)
    longitude = models.CharField(max_length = 100, help_text = "The Reputation Point earnedby the user", default = 0)

    def __str__(self):
        return self.phoneNumber

class Donation(models.Model):
    user = models.ForeignKey(User, on_delete = models.CASCADE)
    date = models.DateField(default = datetime.date.today)

    def __str__(self):
        return self.date

class Verification(models.Model):
    otp = models.CharField(max_length = 4, help_text = "The unique OTP of user", default = 0)
    user = models.ForeignKey(User, on_delete = models.CASCADE, default = 0)
    numberOfTries = models.CharField(max_length = 1, help_text = "The number of times the user has requested the OTP", default = 0)

    def __str__(self):
        return self.otp

class Blood_Bank(models.Model):
    phoneNumber = models.CharField(max_length = 10, help_text = "The Bank Phone Number", default = 1)
    name = models.CharField(max_length = 500, help_text = "The Blood Bank Name", default = 1)
    location = models.CharField(max_length = 500, help_text = "The Bank location in word")
    state = models.CharField(max_length = 500, help_text = "The state of the User", default = 0)
    image = models.CharField(max_length = 2000, help_text = "The image of the campaign", default = 0)
    latitude = models.CharField(max_length = 2000, help_text = "The image of the campaign", default = 0)
    longitude = models.CharField(max_length = 2000, help_text = "The image of the campaign", default = 0)

    def __str__(self):
        return self.name

class Blood_Unit(models.Model):
    blood_group = models.CharField(max_length = 10, help_text = "The Blood Group of the User", default = 0)
    blood_bank = models.ForeignKey(Blood_Bank, on_delete = models.CASCADE)

    def __str__(self):
        return self.blood_group

class Campaign(models.Model):
    blood_bank = models.ForeignKey(Blood_Bank, on_delete = models.CASCADE)
    name = models.CharField(max_length = 500, help_text = "The Name of the User", default = 0)
    # blood_group = models.CharField(max_length = 5, help_text = "The Blood Group of the User", default = 0)
    location = models.CharField(max_length = 500, help_text = "The Location of the User", default = 0)
    state = models.CharField(max_length = 500, help_text = "The state of the User", default = 0)
    image = models.CharField(max_length = 2000, help_text = "The image of the campaign", default = 0)
    description = models.CharField(max_length = 2000, help_text = "The Description of the campaign", default = 0)
    registered_users = models.CharField(max_length = 200, help_text = "The registered number of users in the campaign", default = 0)
    latitude = models.CharField(max_length = 200, help_text = "The registered number of users in the campaign", default = 0)
    longitude = models.CharField(max_length = 200, help_text = "The registered number of users in the campaign", default = 0)
    date = models.DateField(default = datetime.date.today)

    def __str__(self):
        return self.name