from django.db import models
from django.utils.timezone import now

class Notification(models.Model):
    instructor = models.CharField(max_length=100,null=True)
    body = models.CharField(max_length=100,null=True)
    date=models.CharField(max_length=100,null=True)
    priority = models.CharField(max_length=100, null = True)
