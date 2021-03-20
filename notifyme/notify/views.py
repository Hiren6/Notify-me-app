from django.http import HttpResponse
from django.shortcuts import render,redirect
from .models import *
from django.utils.datastructures import MultiValueDictKeyError
from django.http import JsonResponse
from django.utils import timezone
import json

def notify_create(request, c_id):
    if request.method=='POST':
        instructor = request.POST['ins']
        body = request.POST['body']
        date = request.POST['date']
        priority = str(request.POST["priority"])
        print(body)
        post = Notification.objects.create(instructor = instructor, body=body, date=date, priority=priority)
        push_notify(post, c_id)
        post.save()
        return redirect('/')
    else:
        if request.user.is_authenticated:
            return render(request,'notify.html')
        else:
            return redirect('/accounts/login')

def push_notify(post,c_id):
    print("hello")
    from pusher_push_notifications import PushNotifications

    beams_client = PushNotifications(
        instance_id='e5e2e6cd-9f3b-449e-a850-3931a46fedc0',
        secret_key='6F61EE51B348051B01FF3C05DB4ED0009322EFBC70A0592CE89E422F21750255',
    )

    response = beams_client.publish_to_interests(
        interests=[c_id],
        publish_body={
            'apns': {
                'aps': {
                    'alert': 'Message from '+ str(post.instructor)
                }
            },
            'fcm': {
                'notification': {
                    'title': c_id,
                    'body': post.body
                },
                'data': {
                    'title' : c_id,
                    'notifId' : post.id,
                    'date': post.date,
                    'body': post.body,
                    'course': c_id
                }  
            },
            'android':{
                'priority':post.priority
            }
        }
    )

    print(response['publishId'])
