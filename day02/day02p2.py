ROCK = ['X', 'A']
PAPER = ['Y', 'B']
SISSSORS = ['Z', 'C']

LOSE = 'X'
DRAW = 'Y'
WIN = 'Z'

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

def chose(opponent, result):
    if result == DRAW:
        return opponent

    if opponent in ROCK:
        if result == LOSE:
            return SISSSORS[0]
        elif result == WIN:
            return PAPER[0]
    elif opponent in PAPER:
        if result == LOSE:
            return ROCK[0]
        elif result == WIN:
            return SISSSORS[0]
    elif opponent in SISSSORS:
        if result == LOSE:
            return PAPER[0]
        elif result == WIN:
            return ROCK[0]

def main():
    with open("input.txt") as input_file:
        score = 0
        for round_i in input_file:
            aux = round_i.split(' ')
            opponent = aux[0].strip()
            me = aux[1].strip()

            score += getShapePoints(chose(opponent, me))
            if me == WIN:
                score += 6
            elif me == DRAW:
                score += 3
        print("Result:", score)

if __name__ == "__main__":
    main()