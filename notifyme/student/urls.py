from django.urls import path
from . import views

urlpatterns = [
    path("register/", views.register, name = "register"),
    path("login/", views.login, name = "login"),
    path("new_course/", views.new_course, name = "new_course")
]