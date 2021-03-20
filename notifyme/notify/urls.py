from django.urls import path
from . import views 
from django.conf import settings

urlpatterns = [
    path('<str:c_id>/', views.notify_create, name='creation'),
]