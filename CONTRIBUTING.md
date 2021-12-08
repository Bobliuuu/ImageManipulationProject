# Contributing Guidelines 
## General Workflow
Part 1: Pull the current code to your fork of the repository by fetching upstream <br>
Part 2: Make a commit to your fork of the repository <br>
Part 3: Create a pull request contribution <br>
Part 4: Request review from another group member 

## Specifics
### Github Desktop
- Fork the repo and clone it to your local computer
- Click on the fetch origin button to pull the latest version of your repo
- Use the commit feature to upload the code to your fork
- Fetch the upstream and then open a pull request from the web browser
- Check pending issues and pull requests from the web browser

### Git Bash Command Line Interface
Basic commit -> directly to the main branch
```
git add .
git commit -m "Name of commit"
git push origin main
```

Commit from branch 
```
git checkout branch
git merge main
git push 
```

OR

```
git reset --hard main
git push
``` 
