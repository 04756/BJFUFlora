# -*- coding: UTF-8 -*-
#生成判定词典频率统计DF,并附权重
import numpy as np
import pandas as pd
import codecs

with open("G:\lyqcomputer\树木分析\devidingDir\\yeDivDir.txt", 'r',encoding='UTF-8') as f:
    yewords = f.readlines()
with open("G:\lyqcomputer\树木分析\devidingDir\\gjDivDir.txt", 'r',encoding='UTF-8') as f:
    gjwords = f.readlines()
with open("G:\lyqcomputer\树木分析\devidingDir\\huaDivDir.txt", 'r',encoding='UTF-8') as f:
    huawords = f.readlines()
with open("G:\lyqcomputer\树木分析\devidingDir\\guoDivDir.txt", 'r',encoding='UTF-8') as f:
    guowords = f.readlines()


#allword去重
allwords = yewords + gjwords + huawords + guowords
allwords = list(set(allwords))

allwordsSegment=[]
ye=0.0
gj=0.0
hua=0.0
guo=0.0
for word in allwords:
    ye = 0.0
    gj = 0.0
    hua = 0.0
    guo = 0.0
    number=0.0
    if word in yewords:
        ye = 1.0
        if word.find("叶") >=0:
            ye = ye*2
        number = number+1.0
    if word in gjwords:
        gj = 1.0
        if word.find("根") >=0 or word.find("茎") >=0 or word.find("枝") >=0:
            gj = gj*2
        number = number+1.0
    if word in huawords:
        hua = 1.0
        if word.find("花") >=0 or  word.find("雄") >=0 or  word.find("雌") >=0:
            hua = hua*2
        number = number+1.0
    if word in guowords:
        guo = 1.0
        if word.find("果") >= 0 or word.find("种") >= 0:
            guo = guo*2
        number = number+1.0
    if number != 0.0:
        ye = ye / number
        gj = gj / number
        hua = hua / number
        guo = guo / number
    temp = {"word":word,"ye":ye,"gj":gj,"hua":hua,"guo":guo}
    allwordsSegment.append(temp)

ALLdFSg = pd.DataFrame(allwordsSegment)
ALLdFSg.to_csv('G:\lyqcomputer\树木分析\devidingDir\ALLkwordsWeight(DF).csv',index=0)

with open("G:\lyqcomputer\树木分析\devidingDir\\allDivDir.txt", 'w',encoding='UTF-8') as f:
    f.writelines(allwords)