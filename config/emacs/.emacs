;;设置字体
(set-default-font "YaHei Consolas Hybrid-9")

;; 如果你要手工选背景色，可以使用goldenrod
(set-background-color "DarkSlateGray")
;; 我现在使用的是下面的代码，如果你不是从ubuntu软件库中安装的这个
;; 插件，可能需要注释中的这行代码：
;; (require color-theme)
;;(color-theme-gray30)
;; 将mmm-mode的背景色设深一些，与当前的背景色匹配
;;(set-face-background 'mmm-default-submode-face "DarkSlateGray4")

;;(fset 'yes-or-no-p 'y-or-n-p) ; 将yes/no替换为y/n

(display-time-mode 1) ; 显示时间
(setq display-time-24hr-format t) ; 24小时格式
(setq display-time-day-and-date t) ; 显示日期

(mouse-avoidance-mode 'animate) ; 光标移动到鼠标下时，鼠标自动弹开

(setq frame-title-format "%b@六楼的雨") ; 显示当前编辑的文档

;;行号
(global-linum-mode t)

;;插入时间,在A-x 时候输入 insert-date
(defun insert-date ()
  "Insert date at point."
  (interactive)
  (insert (format-time-string "%Y年%m月%e日 %l:%M %a %p")))

;;自动备份的目录
(setq make-backup-files t) 
(setq version-control t) 
(setq kept-old-versions 2) 
(setq kept-new-versions 5) 
(setq delete-old-versions t) 
(setq backup-directory-alist '(("" . "E:/other/EmacsBackup")))
(setq auto-save-file-name-transforms '((".*" "E:/other/EmacsBackup" t)))

(custom-set-variables
 ;; custom-set-variables was added by Custom.
 ;; If you edit it by hand, you could mess it up, so be careful.
 ;; Your init file should contain only one such instance.
 ;; If there is more than one, they won't work right.
 '(column-number-mode t)
 '(inhibit-startup-screen t)
 '(size-indication-mode t))
(custom-set-faces
 ;; custom-set-faces was added by Custom.
 ;; If you edit it by hand, you could mess it up, so be careful.
 ;; Your init file should contain only one such instance.
 ;; If there is more than one, they won't work right.
 )
