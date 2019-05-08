# -*- coding: UTF-8 -*-

import jieba
import re
import pandas as pd
import os
import jieba.posseg as pseg

curPath = os.path.abspath(os.path.realpath(__file__))
curPath = curPath[:curPath.find("\\AnalyseInput")]


frequant = 5000000  #极大词频值

YeWordsDicNames = []
YeWordsPath = curPath+"\\directory\基于HMM分类\叶子"

HuaWordsDicNames = []
HuaWordsPath = curPath+"\\directory\基于HMM分类\花"

GuoWordsDicNames = []
GuoWordsPath = curPath+"\\directory\基于HMM分类\果"

GJWordsDicNames = []
GJWordsPath = curPath+"\\directory\基于HMM分类\根茎"

wordsDicNames = locals()  # 记录txt文本数据
DirNames = []  # 记录文件夹名

#引入分类词典
allWordsWeight = pd.read_csv(curPath+'\\devidingDir\ALLkwordsWeight(DF).csv')
allWords = allWordsWeight["word"]
allWordsList=[]
for w in allWords:
    s = str(w).replace("\n","")
    allWordsList.append(s)
#print(allWordsWeight["ye"][allWordsList.index("一年生草本")])
with open(curPath+"\\devidingDir\\yeDivDir.txt", 'r',encoding='UTF-8') as f:
    yewords = f.readlines()
    Ly = len(yewords)/100
with open(curPath+"\\devidingDir\\gjDivDir.txt", 'r',encoding='UTF-8') as f:
    gjwords = f.readlines()
    Lgj = len(gjwords)/100
with open(curPath+"\\devidingDir\\huaDivDir.txt", 'r',encoding='UTF-8') as f:
    huawords = f.readlines()
    Lhua = len(huawords)/100
with open(curPath+"\\devidingDir\\guoDivDir.txt", 'r',encoding='UTF-8') as f:
    guowords = f.readlines()
    Lguo = len(guowords)/100
with open("G:\刘宇琦计算机\python\\NLPTest\\directory\\tingyongCN.txt", 'r',encoding='UTF-8') as f:
    tywords = f.readlines()

#引入用户自定义词典
with open(curPath+"\\directory\\ye.txt", 'r',encoding='UTF-8') as f:
    Ayewords = []
    tempAwye = f.readlines()
    for aw in tempAwye:
        aw = (str(aw).split(" "))[0]
        Ayewords.append(aw)
with open(curPath+"\\directory\\genjin.txt", 'r',encoding='UTF-8') as f:
    Agjwords = []
    tempAwgj = f.readlines()
    for aw in tempAwgj:
        aw = (str(aw).split(" "))[0]
        Agjwords.append(aw)
with open(curPath+"\\directory\\hua.txt", 'r',encoding='UTF-8') as f:
    Ahuawords = []
    tempAwhua = f.readlines()
    for aw in tempAwhua:
        aw = (str(aw).split(" "))[0]
        Ahuawords.append(aw)
with open(curPath+"\\directory\\guo.txt", 'r',encoding='UTF-8') as f:
    Aguowords = []
    tempAwguo = f.readlines()
    for aw in tempAwguo:
        aw = (str(aw).split(" "))[0]
        Aguowords.append(aw)


#根据判断结果修改相应词频，用极大值方法突出专有名词
def changeAllDirFran(a):
    with open(curPath+"\\directory\\ye.txt", 'r', encoding='UTF-8') as f:
        tempAwye = f.readlines()
    with open(curPath+"\\directory\\genjin.txt", 'r', encoding='UTF-8') as f:
        tempAwgj = f.readlines()
    with open(curPath+"\\directory\\hua.txt", 'r', encoding='UTF-8') as f:
        tempAwhua = f.readlines()
    with open(curPath+"\\directory\\guo.txt", 'r', encoding='UTF-8') as f:
        tempAwguo = f.readlines()
    AllDirLyqWords = []
    tempAwgjLyq = tempAwgj
    tempAwguoLyq = tempAwguo
    tempAwhuaLyq = tempAwhua
    tempAwyeLyq = tempAwye
    if(a=="ye"):
        Lyq = tempAwye
        yeye = []
        for aw in Lyq:
            aw = str(aw).replace(str(frequant)+" ",str(frequant * 200)+" ")
            yeye.append(aw)
        AllDirLyqWords = tempAwgjLyq + tempAwguoLyq + tempAwhuaLyq + yeye
    elif(a=="gj"):
        Lyq = tempAwgj
        gigi = []
        for aw in Lyq:
            aw = str(aw).replace(str(frequant)+" ", str(frequant * 200)+" ")
            gigi.append(aw)
        AllDirLyqWords = gigi + tempAwguoLyq + tempAwhuaLyq + tempAwyeLyq
    elif(a=="hua"):
        Lyq = tempAwhua
        huahua = []
        for aw in Lyq:
            aw = str(aw).replace(str(frequant)+" ", str(frequant * 200)+" ")
            huahua.append(aw)
        AllDirLyqWords = tempAwgjLyq + tempAwguoLyq + huahua + tempAwyeLyq
    elif(a=="guo"):
        Lyq = tempAwguo
        guoguo = []
        for aw in Lyq:
            aw = str(aw).replace(str(frequant)+" ", str(frequant * 200)+" ")
            guoguo.append(aw)
        AllDirLyqWords = tempAwgjLyq + guoguo + tempAwhuaLyq + tempAwyeLyq
    with open(curPath+"\\directory\\all.txt", 'w',encoding='UTF-8') as f:
        f.writelines(list(set(AllDirLyqWords)))


# 针对性修改
# 1.识别形如3-5的数字
def betterMathWord(a):
    pattern = re.compile("[0-9]+/-/[0-9]+/")
    results = pattern.findall(a)
    for rl in results:
        a = a.replace(rl, rl.replace("/", ""))
    return a
#将分类概率排序，寻找分类结果
def findBigger(a={}):
    segment=[]
    big = 0.0
    for key in a:
        if a[key] > big:
            big = a[key]
    if big>0.0:
        for key in a:
            if a[key] >= 0.8*big:
                segment.append(key)
    return segment

#简单分析输入
def easyAnalyseScentense(divideKeyList=[],a = "",path=""):
    sc = a
    pye = 0.0
    pgj = 0.0
    phua = 0.0
    pguo = 0.0
    scwords = betterMathWord("/".join(jieba.cut(sc, HMM=True))).split("/")
    for scw in scwords:
        if scw .__contains__("叶"):
            pye = 1.0
        if scw .__contains__("花"):
            phua = 1.0
        if scw .__contains__("根") or scw .__contains__("茎"):
            pgj = 1.0
        if scw .__contains__("果"):
            pguo = 1.0
    if pye == 1.0:
        divideKeyList.append("ye")
    if phua == 1.0:
        divideKeyList.append("hua")
    if pgj == 1.0:
        divideKeyList.append("gj")
    if pguo == 1.0:
        divideKeyList.append("guo")
    if pye == 0.0 and pguo == 0.0 and pgj == 0.0 and phua == 0.0:
        return False
    return True


#零TF-IDF方法分析句子类别
def analyseScentense(a = "",path=""):
    divideKeyList = []
    if easyAnalyseScentense(divideKeyList,a,path):
        return divideKeyList
    sc = a
    pye = 0.0
    pgj = 0.0
    phua = 0.0
    pguo = 0.0
    scwordsWeight = {}
    jieba.load_userdict(path)
    scwords = betterMathWord("/".join(jieba.cut(sc, HMM=True))).split("/")
    removeWords = []
    for scw in scwords:
        if scw not in allWordsList:
            removeWords.append(scw)
    for rw in removeWords:
        scwords.remove(rw)
    for scw2 in scwords:
        if scw2 in scwordsWeight:
            scwordsWeight[scw2] = scwordsWeight[scw2] + 1.0
        else:
            scwordsWeight[scw2] = 1.0
    for scw3 in scwordsWeight:
        if (allWordsWeight['ye'][allWordsList.index(scw3)] != 0):
            if (pye == 0.0):
                # print(scw3)
                pye = allWordsWeight['ye'][allWordsList.index(scw3)] * scwordsWeight[scw3]
            else:
                # print(scw3)
                pye = allWordsWeight['ye'][allWordsList.index(scw3)] * scwordsWeight[scw3] + pye
        if (allWordsWeight['gj'][allWordsList.index(scw3)] != 0):
            if (pgj == 0.0):
                # print(scw3)
                pgj = allWordsWeight['gj'][allWordsList.index(scw3)] * scwordsWeight[scw3]
            else:
                # print(scw3)
                pgj = allWordsWeight['gj'][allWordsList.index(scw3)] * scwordsWeight[scw3] + pgj
        if (allWordsWeight['hua'][allWordsList.index(scw3)] != 0):
            if (phua == 0.0):
                # print(scw3)
                phua = allWordsWeight['hua'][allWordsList.index(scw3)] * scwordsWeight[scw3]
            else:
                # print(scw3)
                phua = allWordsWeight['hua'][allWordsList.index(scw3)] * scwordsWeight[scw3] + phua
        if (allWordsWeight['guo'][allWordsList.index(scw3)] != 0):
            if (pguo == 0.0):
                # print(scw3)
                pguo = allWordsWeight['guo'][allWordsList.index(scw3)] * scwordsWeight[scw3]
            else:
                # print(scw3)
                pguo = allWordsWeight['guo'][allWordsList.index(scw3)] * scwordsWeight[scw3] + pguo
    pye = pye
    pgj = pgj
    phua = phua
    pguo = pguo
    """
    print(sc)
    print("pgj="+str(pgj))
    print("pye=" + str(pye))
    print("phua=" + str(phua))
    print("pguo=" + str(pguo))
    """
    # print(sc)
    divideKeyList = findBigger({"ye": pye, "gj": pgj, "hua": phua, "guo": pguo})
    return divideKeyList

#遍历树词典
def findDir(dir,key):
    if key in dir:
        return dir
    else:
        for k in dir:
            if isinstance(dir[k],dict):
                return findDir(dir[k],key)
    return None

def anaylse(dir,wordlist,DicNames):
    for word in wordlist:
        #print(word)
        isother = 0  # 判断是否不是所有类别
        for valuename in DicNames:
            #print(word)
            if word in wordsDicNames[valuename]:
                isother = 1
                UseDir = findDir(dir,valuename)
                if UseDir[valuename] != "":
                    dir[valuename] = dir[valuename] + "|" + word
                    #print(valuename+":"+dir[valuename])
                else:
                    dir[valuename] = word
                    #print(valuename+":"+dir[valuename])
        if isother == 0:
            if "other" in dir.keys():
                #print(dir["other"])
                dir["other"] = dir["other"] + "|" + word
            else:
                dir["other"] = word
                #print(dir["other"])
#扫描词典树形成csv文件

#初始化，生成树词典
def input(path,DicNames):
    dic = {}
    for filename in os.listdir(path):
        if os.path.isfile(path + "\\" + filename):
            with open(path + "\\" + filename, 'r', encoding='UTF-8') as f:
                tempcontent = []
                words = f.readlines()
                # 去掉转行符
                for word in words:
                    tempcontent.append(word.replace("\n", ""))
            DicNames.append(filename.replace(".txt", ""))
            wordsDicNames[filename.replace(".txt", "")] = tempcontent
            dic[filename.replace(".txt", "")]=""
        else:
            DirNames.append(filename)
            dic[filename.replace(".txt", "")] = input(path + "\\" + filename,DicNames)
    return dic

#生成植物和器官关系列表
def ToPlantMthod(ToPlant,dir,root):
    for key in dir:
        if not isinstance(dir[key], dict):
            if not dir[key] == "":
                ToPlant.append({"reference": dir[key], "rootName": root})
    else:
        for k in dir:
            if isinstance(dir[k], dict):
                ToPlantMthod(ToPlant, dir[k], root)

def BWByRule(a={}):

    #print(a)

    yedir = input(YeWordsPath,YeWordsDicNames)
    gjdir = input(GJWordsPath,GJWordsDicNames)
    guodir = input(GuoWordsPath,GuoWordsDicNames)
    huadir = input(HuaWordsPath,HuaWordsDicNames)

    #生成植物和器官的关系
    yeToPlant = []
    huaToPlant = []
    guoToPlant = []
    gjToPlant = []

    for key in a:
        if key == "叶":
            anaylse(yedir, a[key], YeWordsDicNames)
        elif key == "花":
            anaylse(huadir, a[key], HuaWordsDicNames)
        elif key == "果":
            anaylse(guodir, a[key], GuoWordsDicNames)
        elif key == "根茎":
            anaylse(gjdir, a[key], GJWordsDicNames)

    ToPlantMthod(yeToPlant, yedir, "叶")
    ToPlantMthod(huaToPlant, huadir, "花")
    ToPlantMthod(guoToPlant, guodir, "果")
    ToPlantMthod(gjToPlant, gjdir, "根茎")

    CTD = []
    CTD.append(huaToPlant)
    CTD.append(yeToPlant)
    CTD.append(gjToPlant)
    CTD.append(guoToPlant)

    yedir.clear()
    huadir.clear()
    gjdir.clear()
    guodir.clear()

    DirNames.clear()
    YeWordsDicNames.clear()
    HuaWordsDicNames.clear()
    GuoWordsDicNames.clear()
    GJWordsDicNames.clear()

    return CTD

def AnalyseInput(Str = ""):
    resultDir=[]
    #完全匹配字符串为最佳匹配放在结果第一行
    resultDir.append(Str)
    #判别语句结果并分词，提取检索要素
    divideKeyList = analyseScentense(Str, curPath+"\\devidingDir\\allDivDir.txt")

    yesegment = []
    gjsegment = []
    guosegment = []
    huasegment = []

    for dk in divideKeyList:
        #print(dk)
        if dk == "ye":
            changeAllDirFran("ye")
            jieba.load_userdict(curPath+"\\directory\\all.txt")
            zxq = "/".join(jieba.cut(Str, HMM=True))
            # print(zxq)
            rel = betterMathWord(zxq).split("/")
            for reli in rel:
                if reli not in tywords and reli in Ayewords:
                    yesegment.append(reli)
        elif dk == "gj":
            changeAllDirFran("gj")
            jieba.load_userdict(curPath+"\\directory\\all.txt")
            zxq = "/".join(jieba.cut(Str, HMM=True))
            # print(zxq)
            rel = betterMathWord(zxq).split("/")
            for reli in rel:
                if reli not in tywords:
                    if reli in Agjwords:
                        gjsegment.append(reli)
        elif dk == "hua":
            changeAllDirFran("hua")
            jieba.load_userdict(curPath+"\\directory\\all.txt")
            zxq = "/".join(jieba.cut(Str, HMM=True))
            # print(zxq)
            rel = betterMathWord(zxq).split("/")
            for reli in rel:
                if reli not in tywords and reli in Ahuawords:
                    huasegment.append(reli)

            lyq = pseg.cut(Str, HMM=True)
            font = 1
            later = 0
            fontWord = ""
            LaterWord = ""
            YanseWord = ""
            for word, flag in lyq:
                if str(flag).__contains__("n") and font == 1 and not re.match(".*色$", word):
                    fontWord = word
                elif str(flag).__contains__("n") and re.match(".*色$", word):
                    YanseWord = word
                    font = 0
                    later = 1
                elif str(flag).__contains__("n") and later == 1 and not re.match(".*色$", word):
                    LaterWord = word
                    later = 0
                    font = 1
                    #颜色匹配加入查询
                    resultDir.append(fontWord+"#"+YanseWord+"#"+LaterWord+" - "+"花")
                    fontWord = word
            if later == 1 and font == 0:
                resultDir.append(fontWord + "#" + YanseWord + "#" + LaterWord + " - " + "花")
        elif dk == "guo":
            changeAllDirFran("guo")
            jieba.load_userdict(curPath+"\\directory\\all.txt")
            zxq = "/".join(jieba.cut(Str, HMM=True))
            # print(zxq)
            rel = betterMathWord(zxq).split("/")
            for reli in rel:
                if reli not in tywords and reli in Aguowords:
                    guosegment.append(reli)
        else:
            jieba.load_userdict(curPath+"\\directory\\all.txt")
            zxq = "/".join(jieba.cut(Str, HMM=True))
            # print(zxq)
            rel = betterMathWord(zxq).split("/")
            for reli in rel:
                if reli not in tywords and reli in Aguowords:
                    guosegment.append(reli)
                elif reli not in tywords and reli in Ahuawords:
                    huasegment.append(reli)
                elif reli not in tywords and reli in Ayewords:
                    yesegment.append(reli)
                elif reli not in tywords and reli in Agjwords:
                        gjsegment.append(reli)
    yesegment = list(set(yesegment))
    gjsegment = list(set(gjsegment))
    huasegment = list(set(huasegment))
    guosegment = list(set(guosegment))
    csvtempD = BWByRule(
        {"叶": yesegment, "根茎": gjsegment, "花": huasegment,"果": guosegment})
    for demo in csvtempD:
        if len(demo) >= 0:
            for item in demo:
                resultDir.append(item["reference"]+" - "+item["rootName"])

    jieba.load_userdict(curPath+"\\directory\\all.txt")
    lyq = pseg.cut(Str, HMM=True)
    for word, flag in lyq:
        #print(word +"   "+ flag)
        if str(flag).__contains__("n"):
            resultDir.append(word + " - " + "None")

    return resultDir

if __name__ == '__main__':
    result = []
    count = 0
    with open(curPath+"\\test.txt","r",encoding="GBK") as f:
        strs = f.readlines()
    for Str in strs:
        count = count + 1
        #print(count)
        temp = AnalyseInput(Str.replace("\n",""))
        result.append(temp)
        #print(temp)
    for i in range(0, len(result)):
        for line in result[i]:
            print(line)
        print("\n")