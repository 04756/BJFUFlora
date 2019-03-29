# -*- coding: UTF-8 -*-
import numpy as np
import pandas as pd
import jieba
import jieba.analyse
import codecs
import os
import findArea
import re

with open("G:\lyqcomputer\树木分析\directory\\ye.txt", 'r',encoding='UTF-8') as f:
    yewords = f.readlines()

#载入数据
path="G:\lyqcomputer\树木分析\叶子"

for filename in os.listdir(path):
    filepath = path + "\\" + filename
    with open(filepath, 'r', encoding='UTF-8') as f:
        tempwords = f.readlines()
        yewords = yewords + tempwords

yewords = list(set(yewords))

with open("G:\lyqcomputer\树木分析\directory\\ye.txt", 'w',encoding='UTF-8') as f:
    f.writelines(yewords)