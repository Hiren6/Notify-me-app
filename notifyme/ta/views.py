from django.shortcuts import render,redirect
from .models import *

def add_ta(request):
    if request.method=='POST':
        instructor=request.POST['ins']
        TA_name=request.POST['name']
        course_id=request.POST['id']
        password=request.POST['pass']
        ta = TA(instructor=instructor, TA_name=TA_name, course_id=course_id, password=password)
        ta.save()
        return redirect('/tas/list')
    else:
        return render(request, 'ta.html')

def list_tas(request):
    tas=TA.objects.filter(instructor=request.user.username)

    return render (request,'ta_list.html',{"obj_list": tas})