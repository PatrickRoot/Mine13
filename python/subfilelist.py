# -*- coding: cp936 -*-
import os

fatherpath=os.getcwd()

menu=open('Ŀ¼(����Ŀ¼).txt','w')

for root,dirs,files in os.walk(fatherpath):
    menu.write(root+'��\n')
    menu.write('�ļ��У�\n')
    if dirs!=[]:
        for d in dirs:
            menu.write(d)
            menu.write('\n')
    else:
        menu.write('kong\n')
    menu.write('\n�ļ���\n')
    if files!=[]:
        for f in files:
            menu.write(f)
            menu.write('\n')
    else:
        menu.write('kong\n')
    menu.write('\n')
menu.close()


