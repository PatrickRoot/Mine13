"""
   Copyright (c) 2016 Sixlab. All rights reserved.

   Under the GPLv3(AKA GNU GENERAL PUBLIC LICENSE Version 3).
   see http://www.gnu.org/licenses/gpl-3.0-standalone.html

   For more information, please see
   http://sixlab.cn/

   @author 六楼的雨/loki
   @since 1.0.0(2016/1/30)
"""
import os
import shutil

electron = 'D:/user/Desktop/dev/electron'
copy = True
# copy = False

dev = True
dev = False

run = True
run = False

def copy_file():
    def ignore(path, names):
        ignored = set()
        endswith = ['.md',
                    '.iml',
                    '.gitignore',
                    '.py',
                    '.git',
                    'LICENSE']

        for name in names:
            for end in endswith:
                if name.endswith(end):
                    ignored.add(name)
        return ignored

    src = os.getcwd()

    dst = electron
    if dev:
        dst += " - dev"
    dst += '/resources/app'
    print(dst)
    print(src)
    shutil.rmtree(dst)
    shutil.copytree(src, dst, ignore=ignore)


def exe_electron():
    exe = electron
    if dev:
        exe += " - dev"
    exe += '/electron.exe'
    os.startfile(exe)


if __name__ == '__main__':
    if copy:
        copy_file()
    if run:
        exe_electron()
