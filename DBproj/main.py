import urllib.request
import simplejson as json
import tkinter
from tkinter import *


global ip
ip="localhost"

global briefTitles

def sift_b_1(text,list1,list2):
    year=text.get()
    type=list1.get(list1.curselection())
    genre=list2.get(list2.curselection())
    global ip
    url = "http://"+ip+":8080/filter?titleType="+type+"&genre="+genre+"&startYear="+str(year)
    response = urllib.request.urlopen(url)
    global briefTitles
    briefTitles = json.load(response)
    global result
    result.delete('1.0', tkinter.END)
    for item in briefTitles:
        res="primaryTitle:\t"+item["primaryTitle"]+'\n'
        res=res+"originalTitle:\t"+item["originalTitle"]+'\n'
        res = res + "type:\t" + item["titleType"] + '\n'
        res = res + "genres:\t"
        if item["genres"] is None:
            res = res
        else:
            for g in item["genres"]:
                res = res + g + " "
        res = res + '\n'
        if item["startYear"]!=0:
            res = res + "startYear:\t" + str(item["startYear"]) + '\n'
        else:
            res = res + "startYear:\tunknown" + '\n'
        if item["endYear"]!=0:
            res = res + "endYear:\t" + str(item["endYear"]) + '\n'
        else:
            res = res + "endYear:\tunknown" + '\n'
        res = res + "averageRating:\t" + str(item["averageRating"]) + '\n'
        res = res + "numVotes:\t" + str(item["numVotes"]) + '\n'
        result.insert(tkinter.END,res+'########################\n')

def search_b_1(text):
    print("person "+text.get())
    name = text.get()
    global ip
    url = "http://" + ip + ":8080/search/figure?name="+name
    response = urllib.request.urlopen(url)
    global briefTitles
    briefTitles = json.load(response)
    global result
    result.delete('1.0', tkinter.END)
    for item in briefTitles:
        res = "primaryName:\t" + item["primaryName"] + '\n'
        if item["birthYear"] != 0:
            res = res + "birthYear:\t" + str(item["birthYear"]) + '\n'
        else:
            res = res + "birthYear:\tunknown" + '\n'
        if item["deathYear"] != 0:
            res = res + "deathYear:\t" + str(item["deathYear"]) + '\n'
        else:
            res = res + "deathYear:\tunknown" + '\n'
        result.insert(tkinter.END,res+'########################\n')
def search_b_2(text):
    print("movie "+text.get())
    name = text.get()
    global ip
    url = "http://" + ip + ":8080/search/title?title="+name
    response = urllib.request.urlopen(url)
    global briefTitles
    briefTitles = json.load(response)
    global result
    result.delete('1.0', tkinter.END)
    for item in briefTitles:
        res = "primaryTitle:\t" + item["primaryTitle"] + '\n'
        res = res + "originalTitle:\t" + item["originalTitle"] + '\n'
        res = res + "type:\t" + item["titleType"] + '\n'
        res = res + "genres:\t"
        if item["genres"] is None:
            res=res
        else:
            for g in item["genres"]:
                res = res + g + " "
        res = res + '\n'
        if item["startYear"] != 0:
            res = res + "startYear:\t" + str(item["startYear"]) + '\n'
        else:
            res = res + "startYear:\tunknown" + '\n'
        if item["endYear"] != 0:
            res = res + "endYear:\t" + str(item["endYear"]) + '\n'
        else:
            res = res + "endYear:\tunknown" + '\n'
        res = res + "averageRating:\t" + str(item["averageRating"]) + '\n'
        res = res + "numVotes:\t" + str(item["numVotes"]) + '\n'
        result.insert(tkinter.END,res+'########################\n')

root=Tk()
root.title("客户端")
root.geometry('900x500')
root.resizable(0,0)

search_frame=Frame(root, width=700,height=500)
search_frame.pack_propagate(0)

sea_entry_fra=Frame(search_frame,width=700,height=50)
sea_entry_fra.grid_propagate(0)

Label(sea_entry_fra,width=10).grid(row=0,column=0)
search_text=StringVar()
search_text.set("")
Entry(sea_entry_fra,textvariable=search_text,width=60).grid(row=1,column=1)
Button(sea_entry_fra,text="search person",command=lambda :search_b_1(search_text)).grid(row=1,column=2)
Button(sea_entry_fra,text="search movie",command=lambda :search_b_2(search_text)).grid(row=1,column=3)
sea_entry_fra.pack(anchor='n')

Label(search_frame,text="result",height=2).pack()
result_frame=Frame(search_frame, width=600,height=400,highlightbackground="black", highlightthickness=1)
result_frame.pack_propagate(0)
global result
result=Text(result_frame, width=600,height=400)
result.pack()
#result.configure(state="disabled")

result_frame.pack()
search_frame.grid(row=0,column=0)

sift_frame=Frame(root,width=200,height=500,highlightbackground="black", highlightthickness=2)
sift_frame.pack_propagate(0)


sift_sel_fra=Frame(sift_frame,width=200,height=500)
sift_sel_fra.grid_propagate(0)

Label(sift_sel_fra).grid(row=0,column=0)

Label(sift_sel_fra,text="year").grid(row=1,column=1)
year_text=IntVar()
year_text.set(2024)
Entry(sift_sel_fra,textvariable=year_text).grid(row=2,column=1)

Label(sift_sel_fra).grid(row=3,column=0)
Label(sift_sel_fra,text="type").grid(row=4,column=1)
url = "http://192.168.3.46:8080/titleTypes"
response = urllib.request.urlopen(url)
types=json.load(response)

type_list=Listbox(sift_sel_fra,height=6,exportselection = False)
for item in enumerate(types):
    type_list.insert("end",item[1])
type_list.grid(row=5,column=1)
Label(sift_sel_fra).grid(row=6,column=1)
Label(sift_sel_fra,text="genre").grid(row=7,column=1)

url = "http://192.168.3.46:8080/genres"
response = urllib.request.urlopen(url)
genres=json.load(response)
genre_list=Listbox(sift_sel_fra,height=6,exportselection = False)
for item in enumerate(genres):
    genre_list.insert("end",item[1])
genre_list.grid(row=8,column=1)
Button(sift_sel_fra,text="yes",command=lambda :sift_b_1(year_text,type_list,genre_list)).grid(row=9,column=1)

sift_sel_fra.pack()

sift_frame.grid(row=0,column=1)

root.mainloop()



'''
url = "http://192.168.3.46:8080/filter?titleType=short&genre=short&startYear=1980"
response = urllib.request.urlopen(url)
data = json.load(response)

# 获取 JSON 数据中的值
name = data[0]['titleId']
age = data[0]['primaryTitle']
city = data[0]['originalTitle']

# 打印用户信息
print("titleId: ", name)
print("primaryTitle: ", age)
print("originalTitle: ", city)
'''