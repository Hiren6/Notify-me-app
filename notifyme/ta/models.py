from django.db import models

class TA(models.Model):
    instructor = models.CharField(max_length=100)
    TA_name = models.CharField(max_length=100)
    course_id = models.CharField(max_length=100)
    password = models.CharField(max_length=100)
