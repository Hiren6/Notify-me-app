from django.shortcuts import render
from .models import *
from courses.models import *
from django.views.decorators.csrf import csrf_exempt
import json
from django.http import StreamingHttpResponse
from django.http import HttpResponse
@csrf_exempt

def register(request):
    if request.method=='POST':
        received_json_data=json.loads(request.body)
        students=Student.objects.all()
        name=[]
        for student in students:
            name.append(student.username)
        username=received_json_data['userName']
        email=received_json_data['email']
        password=received_json_data['password']
        if(username not in name):
            stu=Student(username=username, email=email, password=password, course_id=None)
            stu.save()
            return HttpResponse(
            json.dumps({"validStudent": True}),
            content_type="application/json"
            )

        else:
            return HttpResponse(
            json.dumps({"validStudent": False}),
            content_type="application/json"
            )
@csrf_exempt
def login(request):
    if request.method=='POST':
        received_json_data=json.loads(request.body)
        students=Student.objects.all()
        username=received_json_data['userName']
        password=received_json_data['password']
        print(username, password)
        for student in students:
            if(student.username==username):
                if(student.password==password):
                    return HttpResponse(
                        json.dumps({"validStudent": True}),
                        content_type="application/json"
                        )
        else:
            return HttpResponse(
            json.dumps({"validStudent": False}),
            content_type="application/json"
            )

@csrf_exempt
def new_course(request):
    if request.method=='POST':
        received_json_data=json.loads(request.body)
        courses=Course.objects.all()
        course_id=received_json_data['courseId']
        secret_key=received_json_data['courseKey']
        for course in courses:
            if(course.course_id==course_id):
                if(course.secret_key==secret_key):
                    return HttpResponse(
                        json.dumps({"validStudent": True}),
                        content_type="application/json"
                        )
        else:
            return HttpResponse(
            json.dumps({"validStudent": False}),
            content_type="application/json"
            )
