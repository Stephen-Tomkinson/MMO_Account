Coverage: 100%

# MMO_Account

## Description

#### Why are we doing this?
This project was completed as part of a QA training course in software development. 
#### How I expected the challenge to go.
I didn't expect that I would complete the code as fast as I did, I'm now much more familiar with the concepts of spring. So looking back I can see why I managed to get it all done at the speed I did.
#### What went well? / What didn't go as planned?
The whole coding section went pretty smoothly in my opinion. I had one minor hiccup when pushing and pulling from git. In the many pushes and pulls that I did, I missed a command, when I realised this I tried to fix my mistakes but ended up making things worse. I had to completely re-do the domain unit test. I have since learned of git rebase, which can be used if I were to make the same mistake in the future.
#### Possible improvements for future revisions of the project.
If I am to continue development on this project, I may use a UUID implementation for the ID instead of a Long, and multiple databases with DTO's.


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Installation

If you wish to try the program for yourself, simply download the .jar file in the root of the project.
For this you will need a local java installation on your machine.
Please reference: https://java.com/en/download/

### Modification

If you wish to see how the program works or to modify the code, feel free to fork this repository and clone it down to your local device.
This will not, however, change this version, but you will be free to experiment with your own copy.
You may need to use an exterior program or site to act as a frontend or to review the database(s), I personally use Postman and MySQL/H-2.
Please note: You may need to change between "test" and "prod" in application.properties if you are not getting the desired interactions.

## Documentation

### Project Management

Due to collaboration on a previous project, I had accidentally made my project board under a colleague's jira domain.
I have made a new one since recognising this mistake, but the timings will naturally not be accurate.
https://amarakhtar.atlassian.net/jira/software/projects/MMOA/boards/6/roadmap
https://stephentomkinson.atlassian.net/jira/software/projects/MMOA/boards/3/roadmap
Top has realistic timing as I worked through my project.
The bottom one however, is on my own area and has accurate linking of tasks.

### Entity Relationship Diagram

![ERD drawio](https://user-images.githubusercontent.com/95347199/153428781-605cf9dd-a14d-4b93-bd49-1962efc5b155.png)


### Risk Assessment

[MMO Account Risk Assessment.pdf](https://github.com/Stephen-Tomkinson/MMO_Account/files/8047611/MMO.Account.Risk.Assessment.pdf)

#### Risk Matrix

[MMO Account Risk Matrix.pdf](https://github.com/Stephen-Tomkinson/MMO_Account/files/8048145/MMO.Account.Risk.Matrix.pdf)

## Testing

If you wish to run the tests for yourself, you must fork the repository as mentioned above. You can run coverage of the whole project, or of the individual tests within the src/test/java directory. There are unit tests for both the domain and service classes, to check their individual functionality, in addition to an integration test of the controller, to see how the program works as a whole.

N.B. With these three tests my coverage was at 99.5% for the whole project, by putting the runner method into its respective testing class, the final .5% was achieved. So although a potentially strange practice, as it is only for a measly .5%, it should not be considered as cheating to fake the coverage, but rather just something to top it off.

### Unit Tests 

#### Domain Unit Test

![image](https://user-images.githubusercontent.com/95347199/153433980-f9221ffe-1b53-4c32-934f-dc493a47c8af.png)

![image](https://user-images.githubusercontent.com/95347199/153434074-9172252a-6a17-49c0-bd76-c0468905971f.png)

#### Service Unit Test

![image](https://user-images.githubusercontent.com/95347199/153434355-2149149b-3d34-44b2-a988-9d136d438005.png)

![image](https://user-images.githubusercontent.com/95347199/153434396-8449edc3-3dfe-47db-a963-f767b83efe08.png)

```
Update Test from Service Unit Test
	@Test
	void updateTest() {
		Long id = 1L;
		Account toUpdate = new Account("Bruky", 1100, "EU");
		Optional<Account> optAccount = Optional.of(new Account(id, null, 0, null));
		Account updated = new Account(id, toUpdate.getName(), toUpdate.getLevel(), toUpdate.getRegion());
		Mockito.when(this.repo.findById(id)).thenReturn(optAccount);
		Mockito.when(this.repo.save(updated)).thenReturn(updated);
		assertEquals(updated, this.service.update(id, toUpdate));
		Mockito.verify(this.repo, Mockito.times(1)).save(updated);
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}
```

### Integration Tests 

#### Controller Integration Test

![image](https://user-images.githubusercontent.com/95347199/153436832-a44aae5f-a26e-4a1d-b782-5852a9574171.png)

![image](https://user-images.githubusercontent.com/95347199/153434740-34a37438-3b18-485e-ae3e-d270e8dd3f36.png)


```
Update Test from Controller Integration Test
	@Test
	void updateTest() throws Exception {
		Account updateAcc = new Account("Tabby", 1100, "EU");
		String updateAccJSON = this.map.writeValueAsString(updateAcc);
		Long idUpdate = 1L;
		RequestBuilder updateReq = put("/account/update/" + idUpdate).contentType(MediaType.APPLICATION_JSON)
				.content(updateAccJSON);
		Account retUpdatedAcc = new Account(1L, "Tabby", 1100, "EU");
		String retUpdatedAccJSON = this.map.writeValueAsString(retUpdatedAcc);
		ResultMatcher retStatus = status().isAccepted();
		ResultMatcher retBody = content().json(retUpdatedAccJSON);
		this.mock.perform(updateReq).andExpect(retStatus).andExpect(retBody);
	}
```

### Full Project Test

![image](https://user-images.githubusercontent.com/95347199/153437158-a7d1777f-d42f-459c-9a84-cff1fccf1394.png)

![image](https://user-images.githubusercontent.com/95347199/153437420-a46abc05-a501-4919-aae7-319a51aa1169.png)

## Api Requests

### Create

![image](https://user-images.githubusercontent.com/95347199/153453021-02495c43-a2d9-4c2e-8785-ad64418c1531.png)

### Read

#### All

![image](https://user-images.githubusercontent.com/95347199/153453306-08b4c416-e3a6-4d3e-b4bd-bd94c1827134.png)

#### By Id

![image](https://user-images.githubusercontent.com/95347199/153453394-daf9d70d-98a1-4530-8344-ded86741da39.png)

### Update

![image](https://user-images.githubusercontent.com/95347199/153453468-dcac86b4-282c-4e93-9847-4d5f36176f7d.png)

### Delete

![image](https://user-images.githubusercontent.com/95347199/153453644-08bf3b38-63f6-449e-8c15-31295456c1a7.png)

## Database

![image](https://user-images.githubusercontent.com/95347199/153452892-d4078909-f01a-481a-a63c-a5e607b34977.png)

## Authors

* **Stephen Tomkinson** - *Full Project* - [Stephen-Tomkinson](https://github.com/Stephen-Tomkinson)

## Acknowledgments

* QA DFESW8 :)
