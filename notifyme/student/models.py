from django.db import models

class Student(models.Model):
    username = models.CharField(max_length=100)
    course_id = models.CharField(max_length=100,null=True)
    email = models.CharField(max_length=100)
    password = models.CharField(max_length=100)
