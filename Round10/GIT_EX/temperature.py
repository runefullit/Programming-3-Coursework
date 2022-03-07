
def main():

    try:
        tdir = input("Select transform direction (CF) or (FC): ")
        input_temp = float(input("Input temperature: "))
    except:
        print("Not valid input")
        exit()

    if tdir == "CF":
        print("{} C = {} F".format(input_temp, t_transform(input_temp, 'c')))
    elif tdir == "FC":
       print("{} F = {} C".format(input_temp, t_transform(input_temp, 'f')))
    else:
        print("Not valid direction.")




def t_transform(temp, type):

    if type == 'c':
        return float(temp * 9/5 + 32)
    elif type == 'f':
        return float((temp-32) * 5/9)

    return None


if __name__ == "__main__":
    main()
