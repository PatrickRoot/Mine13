# -*- coding: utf-8 -*-
#将本目录下的xv格式文件转换为flv格式
import os

oldfileslist=os.listdir(os.getcwd())
if os.path.exists(u'转码后文件')==False:
    os.mkdir(u'转码后文件')

for oldfilename in oldfileslist:
    filename=os.path.splitext(oldfilename)
    if filename[1]!='.xv':
        continue
    oldfile = open(oldfilename,'rb')
    newfilename = u'转码后文件\\'+filename[0]+'.flv'
    newfile = open(newfilename,'wb')

    temp=oldfile.read()
    tempfile=temp[0x00200000:]
    tempfile1=tempfile[:0x400]

    oldmagic=ord(tempfile1[0])
    newmagic=0x46

    if newmagic<oldmagic:
        newmagic += 0x100
    magicnum=newmagic-oldmagic

    for i in range(0,0x400):
        tempnum=ord(tempfile1[i])+magicnum
        if tempnum>0xFF:
            tempnum -= 256
        tempfile1=tempfile1[:i]+chr(tempnum)+tempfile1[i+1:]

    tempfile=tempfile1+tempfile[0x400:]
    newfile.write(tempfile)

    newfile.close()
    oldfile.close()
    filefinish=u'%s 已经完成'% filename[0]
    print filefinish
