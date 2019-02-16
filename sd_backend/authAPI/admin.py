from django.contrib import admin
from authAPI.models import User, Verification

# Register your models here.

admin.site.register(User)
admin.site.register(Verification)