# -*- coding: utf-8 -*-

"""
Module implementing Dialog.
"""

from PyQt4.QtCore import pyqtSlot
from PyQt4.QtGui import QDialog, QApplication

from Ui_UrlCon import Ui_Dialog

import base64

class Dialog(QDialog, Ui_Dialog):
    """
    Class documentation goes here.
    """
    def __init__(self, parent=None):
        """
        Constructor
        
        @param parent reference to the parent widget (QWidget)
        """
        super().__init__(parent)
        self.setupUi(self)
    
    @pyqtSlot()
    def on_textEdit_textChanged(self):
        """
        Slot documentation goes here.
        """
        # TODO: not implemented yet
        url = self.textEdit.toPlainText()
        urlSplit = url.split("?")
        actionUrl = urlSplit[0].split("/")[-1].split('.')[0]
        self.lineEdit.setText(actionUrl)
        varUrl = urlSplit[1]
        if varUrl.startswith('_QUERY_STRING='):
            base64String = varUrl.split("=")[1]
            vars = base64.urlsafe_b64decode(base64String+'==').decode()
            self.textEdit.setText(urlSplit[0]+"?"+vars)
            isBase64String = True
        else:
            vars = urlSplit[1]
            isBase64String = False
        input =''
        if vars is not None and vars != "":
            varList = vars.split("&")
            for var in varList:
                varSplit = var.split("=")
                type = varSplit[0]
                if type != 'no_sitemesh':
                    value = varSplit[1]
                else:
                    value = ''
                if type == 'method':
                    print(value)
                    self.lineEdit_2.setText(value)
                input+=type+"\n"+value+"\n----------------------\n"
            if isBase64String:
                input+=url
            self.textEdit_2.setText(input)
        self.lineEdit_2.setFocus()
        self.lineEdit_2.selectAll()

if __name__ == "__main__":
    import sys
    app = QApplication(sys.argv)
    myApp = Dialog()
    myApp.show()
    sys.exit(app.exec_())
