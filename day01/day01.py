
def main():
    filename = 'input.txt'
    elfs_calories = []
    acc_calories = 0
    with open(filename) as input_file:
        lines = input_file.read().split("\n")
        for i, line in enumerate(lines):
            if line.strip() == "" or i == (len(lines) - 1):
                elfs_calories.append(acc_calories)
                acc_calories = 0
            else:
                acc_calories += int(line)
        
        elfs_calories.sort(reverse=True)
        print("Elf #1:", elfs_calories[0])
        print("Elf #2:", elfs_calories[1])
        print("Elf #3:", elfs_calories[2])
        print("Sum of Calories:", sum(elfs_calories[:3]))

if __name__ == "__main__":
    main()