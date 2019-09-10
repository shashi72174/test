input = "ee kannada"

vowels = ['a','e','i','o','u']
count = 0

def indexOf(vowels,inputChar):
    for i in range(0,len(vowels)) :
        if inputChar == vowels[i]:
            return i


    return -1

for i in input :
    if indexOf(vowels,i) != -1:
        count = count+1

print(count)



