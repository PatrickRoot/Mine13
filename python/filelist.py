# -*- coding: cp936 -*-
#�г���ǰĿ¼������mp4��ʽ�ļ��б�
import os
fileslist=os.listdir(os.getcwd())
newfile = open("Ŀ¼.txt",'w')
i=0
lenght=len(fileslist)
tempfile=""
for everyfile in fileslist:
    i=i+1
    filename=os.path.splitext(everyfile)
    if filename[1]!='.mp4':
        print "%d/%d �Ѿ����"% (i,lenght)
        continue
    newfile.write(filename[0]+"\n")
    print "%d/%d �Ѿ����"% (i,lenght)
newfile.close()
print "���"
