from django.db import models
from django.utils.timezone import now

class Course(models.Model):
    instructor = models.CharField(max_length=100)
    course_name = models.CharField(max_length=100)
    course_id = models.CharField(max_length=100)
    secret_key = models.CharField(max_length=100)
    def __str__(self):
        return f'{self.course_id}'

    class Meta:
        db_table = 'course_table'
    