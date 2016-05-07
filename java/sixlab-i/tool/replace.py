import os；

class Replace:
    fileTxt = 'file.txt';
    pathTxt = 'path.txt';
    contain_str = '/sixlab/sixlab-i/res';
    replace_str = 'http://cdn.sixlab.cn/res/';
    replace_type = ',txt,html,css,';
    
    def __init__(self):
        pass
        
    def replace(self):
        self.replaceFile();
        self.replacePath();

    def replacePath(self):
        f = open(self.fileTxt,'r');
        lines = f.readlines();
        for line in lines:
            line = line.strip('\n').strip('\r').replace('\\',"/");
            for root,dirs,files in os.walk(line):
                for file in files:
                    filePath = root+"/"+file;
                    self.dealFile(filePath);

    def replaceFile(self):
        f = open(self.fileTxt,'r');
        lines = f.readlines();
        for line in lines:
            line = line.strip('\n').strip('\r');
            if len(line) == 0:#Zero length indicates EOF
                break;
            self.dealFile(line);

    def dealFile(self, filePath):
        array = filePath.split('.');
        type = array[-1];
        if type not in self.replace_type:
            break;
        print("-------------处理文件："+filePath);
        f = open(filePath.replace('\\',"/"),'r');
        temp = "";
        has_str = False;
        lines = f.readlines();
        for line in lines:
            if len(line) == 0:#Zero length indicates EOF
                temp+=line;
                break
            if self.contain_str in line:
                print("\t匹配："+line);
                has_str = True;
                temp+= line.replace(self.contain_str,self.replace_str);
            else:
                temp+=line;
        f.close();
        if has_str:
            f = open(filePath,'w');
            f.write(temp);
            f.close();

if __name__=="__main__":
    replace = Replace();
    replace.replace();