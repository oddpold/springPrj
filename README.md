# springPrj
name: Update Revision Dates

on: gollum

jobs:
  updateWikiRevision:
    if: github.actor != 'actions-user'

    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
        with:
          repository: ${{github.repository}}.wiki

      - name: dump github context
        env:
          GITHUB_CONTEXT: ${{ toJson(github) }}
        run: |
          echo "$GITHUB_CONTEXT"
          
      - name: check wiki repo content
        run: |
          ls -al
          
      - name: check the most rescent wiki git logs
        run: |
          git log -1 --stat
      
      - name : create revision history markdown string
        env:
          REPO: ${{github.repository}}
          PAGENAME: ${{github.event.pages[0].page_name}}
        run: |
          git log -1 --stat > log.txt
          cat log.txt | grep "Author" | awk '{print "[author["$2"] updated wiki at "}' > author.txt
          cat log.txt | grep "Date"   | awk '{print "date["$6" "$2" "$3" "$4" "$5"]]"}' > date.txt
          cat log.txt | grep "commit" | awk -v repo="$REPO" -v pagename="$PAGENAME" '{print "(https://github.com/"repo"/wiki/"pagename"/"$2")<br/>"}' > link.txt
          paste -d'\0' author.txt date.txt link.txt > mdstring.txt
          
      - name: write new revision history
        run: |
          awk 'NR>2{while((getline a < "mdstring.txt") > 0){print a}}1' Design-and-Planning.md > Design-and-Planning-revised.md
          awk 'NR>2{while((getline a < "mdstring.txt") > 0){print a}}1' Requirements-and-Specification.md > Requirements-and-Specification-revised.md

      - if: github.event.pages[0].page_name == 'Design-and-Planning'
        name: if log inlcudes Design-and-Planning.md change, then update.
        run: |
          cat log.txt | awk '{if(NR>=7) print $1}' | grep "Design-and-Planning.md" > condition.txt
          if [[ $(wc -l <condition.txt) -ge 1 ]]; then mv Design-and-Planning-revised.md Design-and-Planning.md;fi
      
      - if: github.event.pages[0].page_name == 'Requirements-and-Specification'
        name: if log inlcudes Requirements-and-Specification.md change, then update.
        run: |
          cat log.txt | awk '{if(NR>=7) print $1}' | grep "Requirements-and-Specification.md" > condition.txt
          if [[ $(wc -l <condition.txt) -ge 1 ]]; then mv Requirements-and-Specification-revised.md Requirements-and-Specification.md;fi
          
      - name : cleanup temporary file
        run: |
          rm log.txt
          rm author.txt
          rm date.txt
          rm link.txt
          rm mdstring.txt
          rm -f rm condition.txt
          rm -f Design-and-Planning-revised.md
          rm -f Requirements-and-Specification-revised.md
          
      - name: commit changes
        run: |
          git config --local user.email "action@github.com"
          git config --local user.name "GitHub Action"
          git add .
          git commit -m "update revision history"
          
      - name: Push to wiki repo
        uses: ad-m/github-push-action@master
        with:
          repository: ${{github.repository}}.wiki    # specify the wiki repo and push the update.
          github_token: ${{ secrets.GITHUB_TOKEN }}
