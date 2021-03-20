from django.urls import path
from . import views

urlpatterns = [
    path('list', views.list_courses, name="list"),
    path('', views.add_course, name="courses")
]