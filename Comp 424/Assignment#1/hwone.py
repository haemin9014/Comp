import os
import re
import string
import collections
import math
from array import *
from itertools import permutations
from pycipher import Caesar, ColTrans
from collections import OrderedDict

cipher_text = 'KUHPVIBQKVOSHWHXBPOFUXHRPVLLDDWVOSKWPREDDVVIDWQRBHBGLLBBPKQUNRVOHQEIRLWOKKRDD'
saved = []#save after we decryp, list(standard array)
permutations_list = []
max_counting=[]#list(standard array)
frequent = []#save top 7 frequent list, list(standard array) 
result = []

#start simple shift substitution decryption
#need to install pip install pycipher
#https://www.youtube.com/watch?v=vPpRkHUPX_Q
def decryp(cipher, trans):
    
    #this is a collection of alphabet
    sfs = collections.deque(string.uppercase)

    #allow to shift array, trans is saying how many time we are going to shift it.
    sfs.rotate(trans)

    #remove '' and ,
    sfs = ''.join(list(sfs))

    #translate certain value to something else to use translate, we must use maketrans
    #maketrans allow to put in proper table
    #maketrans(from, to)
    # return translation table suitable for passing to translate(), that will map each character in from in the character at the same
    # position in to; from and to must have the same length.
    return cipher.translate(string.maketrans(string.uppercase, sfs))


#add a number of frequent numbers. top 6
def frequencies(text):
    #keep the shift count if shifted char matches most common
    #by http://pi.math.cornell.edu/~mec/2003-2004/cryptography/subs/frequencies.html
    total_count = 0   
    #count(char) use how many char we have in the string     
    total_count += (12.02 * text.count('E')) 
    total_count += (9.10 * text.count('T'))
    total_count += (8.12 * text.count('A'))
    total_count += (7.68 * text.count('O'))
    total_count += (7.31 * text.count('I'))
    total_count += (6.95 * text.count('N'))
    total_count += (6.28 * text.count('S'))
    #save the frequency level number in to list
    max_counting.append(total_count)
    #find that have > 200 and save in to frequent list
    if total_count > 200:
        frequent.append(text)
    #since we need to do multiple list, turn in to 0
    total_count = 0
         
    return frequent


# #breaking Columnar Transposition Cipher
#http://practicalcryptography.com/cryptanalysis/stochastic-searching/cryptanalysis-columnar-transposition-cipher/
#by this article, we need purmutations and word list.
#got recommend to use
#https://docs.python.org/2/library/itertools.html
#got recommend to use
#https://pycipher.readthedocs.io/en/master/

#dictionary list got dictionary list from
#https://www.mit.edu/~ecprice/wordlist.10000
#make and dictionary
def word_list():
    with open('word_list.txt', 'r') as words:
        return words.read().split()

#using the world_list, we need to do dictionary attack
#tried to figure out how to do dictionary attack, but could not code it by my self so
#searching interent, found it how to break columnar transposition from
#https://allejo.io/blog/breaking-columnar-transposition-and-caesar-cipher/
#https://www.geeksforgeeks.org/columnar-transposition-cipher/

####################################################################################################################################################
def attack(permute, dictionary, number):
    after_attack = {}
    #since, we are going to check the word list and breaking columnar, we need to have place to  save decrypt list and words
    for de_list, string in permute.iteritems():
        after_attack[string] = {
            'de_list' : de_list,
            'dictionary' : []
        }

        for _word in dictionary:
            word = _word.strip().upper()

            if len(word) < 3:
                continue
            
            if word in string:
                after_attack[string]['dictionary'].append(word)

    sortedResults = OrderedDict(sorted(
        after_attack.iteritems(),
        key=lambda x: len(x[1]['dictionary']),
        reverse=True
    ))
    #we will put in multiple cipher, and decrypt it, each of them will have
    #their own text file.
    with open("{}st dcrypt.txt".format(number), "w") as f:
        for k in sortedResults:
            line = "{}: [{}]\n".format(
                ', '.join([sortedResults[k]['de_list'], k ]),
                ', '.join(sortedResults[k]['dictionary'])
            )
            f.write(line)

    return after_attack

def Columnar(second, keyLength):
    permute = permutations(keyLength)
    columnPosition = {}

    for order in permute:
        key = ''.join(order)
        columnPosition[key] = ColTrans(key).decipher(second)
 
    return columnPosition
################################################################################################################################################


#method for saving the result
def solved_result(o):
    with open("{}st dcrypt.txt".format(o), "r") as s:
        result.append(s.readline())

    return result

#method for sort
def sort_length(answer):
    answer.sort(key=len)
    return answer

if __name__ == '__main__':
    for i in range(25):
        saved.append(decryp(cipher_text, i + 1))

    # for j in range(len(saved)):
    #     print(saved[j])
    # print("using freq")
    print("making frequencies list...after simple shift substitution decryption")
    for j in range(25):
        #this function will save in frequent[]
        frequencies(saved[j])

    # this for loop is for printing frequent list.    
    # for k in range(len(frequent)):
    #     print(frequent[k])

    print("finished simple shift substitution decryption")

    print("creating word list for Columnar Transposition Cipher")
    load_words = word_list()

    print("starting permutations and saving, it will take some time")
    for m in range(len(frequent)):
        permutations_list.append(Columnar(frequent[m],'0123456'))
    print("finished, completed saving, start dictionary attack......")
    print("this method will take little long")
    # for number in range(len(frequent)):
    #     #put dictionary attak in here using frequent[number]
    #     print("finished {} dictionary attack , {}'s dictionary attack result text file will be saved in the file").format(number)
    for n in range(len(frequent)):
        attack(permutations_list[n], load_words, n)
    
    print("finish dictionary attack")
    #read and save first all first line of text file and save at result.

    print("saving all first line of text file to the result.")
    for o in range(len(frequent)):
        solved_result(o)

    print("removing all text file")
    for d in range(len(frequent)):
        file_name = ("{}st dcrypt.txt").format(d)
        os.remove(file_name)

    #print out result
    #sort result list by length, it will sorted smallest to longest
    sort_length(result)
    #saved the longest one in final_part
    final_part = result[len(result)-1]
    #erased after all char after :
    answer = final_part[0: final_part.find(":"):]
    #print out the result except in and ,
    print("as a result, answer is : {}").format(answer.strip("01234567890,"))






