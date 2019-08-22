import java.util.Scanner;


class MainClass
{

	public static void main(String[] args)
	{
		int id, noOfBooks_Student, noOfBooks_Library;
		String name;
		Scanner scannerInt = new Scanner(System.in);
		Scanner scannerString = new Scanner(System.in);
		

		System.out.println("Enter number of students");
		int n = scannerInt.nextInt();
		
		Student studentObjects[] = new Student[n];
		
		for(int i = 0; i < n; i++)
		{
			System.out.println("Enter id of student " + (i+1));
			id = scannerInt.nextInt();
			System.out.println("Enter name of student " + (i+1));
			name = scannerString.nextLine();
			System.out.println("Enter number of books of student " + (i+1));
			noOfBooks_Student = scannerInt.nextInt();
			studentObjects[i]= new Student(id, name, noOfBooks_Student); 
		}
		System.out.println("Enter number of books in library");
		noOfBooks_Library = scannerInt.nextInt();
		Library lib = new Library(noOfBooks_Library);

		int choice = 0;
		while(choice != -1)
		{
			
			System.out.println("Enter a chocie\n1. Accessing a student object\n2. Accessing library object\n-1. Exit the program");
			choice = scannerInt.nextInt();
			switch(choice)
			{
			case 1:
				accessStudent(studentObjects, lib);
				break;
			case 2:
				accessLibrary(lib);
				break;
			case -1:
				break;
			}
		}

		scannerInt.close();
		scannerString.close();
	}
	
	private static void accessStudent(Student studentObjects[], Library lib)
	{
		Scanner num = new Scanner(System.in);
		System.out.println("Enter the student number you want to access");
		int i = num.nextInt();
		i--;

		int choice = 0;
		while(choice != -1)
		{
			System.out.println("Enter a choice\n1. Borrow a book\n2. Return a book\n3. Display details of the student\n-1. Exit this student");
			
			choice = num.nextInt();
			switch(choice)
			{
				case 1:
					studentObjects[i].borrowBook(lib);
					break;
				case 2:
					studentObjects[i].returnBook(lib);
					break;
				case 3:
					displayStudent(studentObjects[i]);
					break;
				case -1:
					break;
			}
		}
	}
	
	private static void accessLibrary(Library lib)
	{
		Scanner num = new Scanner(System.in);
		
		int choice = 0;
		
		while(choice != -1)
		{
			System.out.println("Enter a choice\n1. Increment number of books in the library\n"
					+ "2. Decrement number of books in the library\n3. Display total number of books in the library"
					+ "\n-1. Exit the library");
			
			choice = num.nextInt();
			switch(choice)
			{
				case 1:
					lib.incrBooks();
					break;
				case 2:
					lib.decrBooks();
					break;
				case 3:
					System.out.println("\nNumber of books in the library is " + lib.noOfLibBooks + "\n");
					break;
				case -1:
					break;
			}
		}
	}
	
	private static void displayStudent(Student studentObj)
	{
		System.out.println("\nThe id of the selcted student is " + studentObj.id);
		System.out.println("The name of the selcted student is " + studentObj.name);
		System.out.println("The number of books of the selcted student is " + studentObj.noOfBooks + "\n");
	}
}


class Student
{
	int id;
	String name;
	int noOfBooks;
	
	void borrowBook(Library lib)
	{
		if(lib.noOfLibBooks != 0)
		{
			lib.decrBooks();
			noOfBooks++;
			System.out.println("\nNow your number of books is " + noOfBooks);
			System.out.println("Number of books in library is " + lib.noOfLibBooks + "\n");
		}
		else
			System.out.println("\nNo books currently in the library\n");
	}
	
	void returnBook(Library lib)
	{
		if(noOfBooks != 0)
		{
			lib.incrBooks();
			noOfBooks--;
			System.out.println("\nNow your number of books is " + noOfBooks);
			System.out.println("Number of books in library is " + lib.noOfLibBooks + "\n");

		}
		else
			System.out.println("\nYou don't have any books to return...\n");
	}
	
	public Student(int id, String name, int noOfBooks)
	{
		this.id = id;
		this.name = name;
		this.noOfBooks = noOfBooks;
	}
	
	public Student(){}
}


class Library
{
	public int noOfLibBooks;
	void incrBooks()
	{
		noOfLibBooks++;
		System.out.println("\nNumber of books in library is " + noOfLibBooks + "\n");
	}
	
	void decrBooks()
	{
		if(noOfLibBooks != 0)
		{
			noOfLibBooks--;
			System.out.println("\nNumber of books in library is " + noOfLibBooks + "\n");
		}
		else
			System.out.println("\nNo books in the library\n");
	}
	
	void returnTotalBooks()
	{
		System.out.println("Number of books in the library is " + noOfLibBooks + "\n");
	}
	
	public Library(int noOfLibBooks)
	{
		this.noOfLibBooks = noOfLibBooks;
	}
}

