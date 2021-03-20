from django.shortcuts import render,redirect
from django.http import HttpResponse
from django.contrib.auth.forms import UserCreationForm
from django.contrib.auth import login,logout,authenticate

# Create your views here.
def home(request):
    if request.user.is_authenticated:
        return redirect('/courses/list')
    else:
        return render(request, 'home.html', {'name':'Welcome to Dashboard'})