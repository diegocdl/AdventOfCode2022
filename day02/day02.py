ROCK = ['X', 'A']
PAPER = ['Y', 'B']
SISSSORS = ['Z', 'C']
def isRock(play):
    return play in ROCK

def isPaper(play):
    return play in PAPER

def isSissors(play):
    return play in SISSSORS

def getShapePoints(shape):
    if shape in ROCK:
        return 1
    elif shape in PAPER:
        return 2
    elif shape in SISSSORS:
        return 3
    else:
        return 0

def isEqual(opponent, me):
    return (ord(opponent) - ord('A')) == (ord(me) - ord('X'))


def main():
    with open("input.txt") as input_file:
        score = 0
        for round_i in input_file:
            aux = round_i.split(' ')
            opponent = aux[0].strip()
            me = aux[1].strip()

            score += getShapePoints(me)
            if isRock(opponent) and isPaper(me):
                score += 6
            elif isPaper(opponent) and isSissors(me):
                score += 6
            elif isSissors(opponent) and isRock(me):
                score += 6
            elif isEqual(opponent, me):
                score += 3
        print("Result:", score)

if __name__ == "__main__":
    main()