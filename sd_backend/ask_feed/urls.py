from django.urls import path
from . import views

urlpatterns = [
    path('', views.ask_feed, name="ask_feed"),
]