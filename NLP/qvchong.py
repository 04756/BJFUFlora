frequant = 5000000

def getfrequant(a):
    words = []
    for word in a:
        word = word.replace("5000000", str(frequant))
        print(word)
        words.append(word)
    print(words)
    return words

"""
with open("G:\lyqcomputer\树木分析\devidingDir\\yeDivDir.txt", 'r',encoding='UTF-8') as f:
    dyewords = f.readlines()
with open("G:\lyqcomputer\树木分析\devidingDir\\gjDivDir.txt", 'r',encoding='UTF-8') as f:
    dgjwords = f.readlines()
with open("G:\lyqcomputer\树木分析\devidingDir\\huaDivDir.txt", 'r',encoding='UTF-8') as f:
    dhuawords = f.readlines()
with open("G:\lyqcomputer\树木分析\devidingDir\\guoDivDir.txt", 'r',encoding='UTF-8') as f:
    dguowords = f.readlines()
"""
with open("G:\lyqcomputer\树木分析\directory\\ye.txt", 'r',encoding='UTF-8') as f:
    yewords = f.readlines()
    yewords = getfrequant(yewords)
with open("G:\lyqcomputer\树木分析\directory\\genjin.txt", 'r',encoding='UTF-8') as f:
    gjwords = f.readlines()
    gjwords = getfrequant(gjwords)
with open("G:\lyqcomputer\树木分析\directory\\hua.txt", 'r',encoding='UTF-8') as f:
    huawords = f.readlines()
    huawords = getfrequant(huawords)
with open("G:\lyqcomputer\树木分析\directory\\guo.txt", 'r',encoding='UTF-8') as f:
    guowords = f.readlines()
    guowords = getfrequant(guowords)
"""
yewords = yewords + dyewords
gjwords = gjwords + dgjwords
huawords = huawords + dhuawords
guowords = guowords + dguowords

yewords = list(set(yewords))
gjwords = list(set(gjwords))
huawords = list(set(huawords))
guowords = list(set(guowords))
"""
with open("G:\lyqcomputer\树木分析\directory\\ye.txt", 'w',encoding='UTF-8') as f:
    f.writelines(yewords)
with open("G:\lyqcomputer\树木分析\directory\\genjin.txt", 'w',encoding='UTF-8') as f:
    f.writelines(gjwords)
with open("G:\lyqcomputer\树木分析\directory\\hua.txt", 'w',encoding='UTF-8') as f:
    f.writelines(huawords)
with open("G:\lyqcomputer\树木分析\directory\\guo.txt", 'w',encoding='UTF-8') as f:
    f.writelines(guowords)