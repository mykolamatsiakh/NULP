pattern = ["a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
               "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"]


def add_arr(blist):
    count = 0
    n = 0
    k = 0
    for element in blist:
        for element_x in blist:
            element_a = element + element_x
            element_a = set(element_a)
            length_of_element = len(element_a)
            my_pattern = pattern[:length_of_element]
            if element_a.issubset(my_pattern) and len(element_a) <= 29:
                count = count + 1
                print "ok: ", element_a
                del element_x, element
                del blist[k]
                del blist[n]
                break
            else:
                print "neOK: ", element_a
        n = n+1
    k = k+1
    print "Count:", count


if __name__ == '__main__':
    list = open('input_2.txt', 'r').readlines()
    for i in range(list.__len__()):
        list[i] = str(list[i].strip('\n'))
    add_arr(list)


