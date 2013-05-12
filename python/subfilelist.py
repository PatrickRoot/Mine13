# -*- coding: utf-8 -*-
import os

fatherpath=os.getcwd()

menu=open(u'目录(含子目录).txt','w')

for root,dirs,files in os.walk(fatherpath):
    menu.write(root+'：\n')
    menu.write(u'文件夹：\n')
    if dirs!=[]:
        for d in dirs:
            menu.write(d)
            menu.write('\n')
    else:
        menu.write('kong\n')
    menu.write(u'\n文件：\n')
    if files!=[]:
        for f in files:
            menu.write(f)
            menu.write('\n')
    else:
        menu.write('kong\n')
    menu.write('\n')
menu.close()


