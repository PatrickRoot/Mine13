# -*- coding: utf-8 -*-
#列出当前目录下所有mp4格式文件列表
import os
fileslist=os.listdir(os.getcwd())
newfile = open(u"目录.txt",'w')
i=0
lenght=len(fileslist)
tempfile=""
for everyfile in fileslist:
    i=i+1
    filename=os.path.splitext(everyfile)
    if filename[1]=='.mp4':
        print u"%d/%d 已经完成"% (i,lenght)
        continue
    newfile.write(filename[0]+"\n")
    print u"%d/%d 已经完成"% (i,lenght)
newfile.close()
print u"完成"
