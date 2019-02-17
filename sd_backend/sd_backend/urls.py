"""sd_backend URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/2.1/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path, include
from sd_backend import views

urlpatterns = [
    path('admin/', admin.site.urls),
    path('auth/', include('authAPI.urls')),
    path('splash/', views.splash, name='splash'),
    path('fcm/', views.fcm, name="fcm"),
    path('req/', views.req, name="request"),
    path('bank/create_campaign/', views.bank_create, name="bank_create_campaign"),
    path('bank/req/', views.bank_req, name="bank_req"),
    path('request_blood/', views.request_blood, name="request_blood"),
    path('add_donation/', views.add_donation, name="add_donation"),
    path('bank/campaign/req', views.campaign_list, name="campaign_list"),
    # path('view_donations/', views.view_donations, name="view_donations"),
    path('bank/update/', views.bank_update, name="update_banks")
]