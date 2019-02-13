from django.urls import path
from . import views

urlpatterns = [
    path('', views.ask_feed, name="ask_feed"),
    path('new/', views.new_ask, name="new_ask"),
]