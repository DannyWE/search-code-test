# search-code-test
The code test for search ticket, user and organisation 

## :dog: Setup
This project needs Java `8` and Scala `2.12.3`
[Install Java](https://java.com/en/download/help/index_installing.xml)

```bash
# Install Scala
$ brew install scala
$ brew install sbt

# Compile project
$./sbt clean compile
```
---

## :rabbit: Running
```bash
$./sbt run
```

## :bear: Testing
```bash
$./sbt test
```

---

## :koala: the relationship of those JSON files
<p align="center">
    <img src="https://github.com/DannyWE/search-code-test/blob/master/lib/code-test-diagram.jpg" width="546">
</p>

Organization has one to many relationship with Users and Tickets
User has one to many relationship with Tickets.

One thing should be noticed that user can be assigner and submitter for one ticket.

---


## :cat: The code design 
After a second thought about the solution, I would like to include pre-processing work to flatten the json result before the search. 
The reason is for fast searching. It is not practical to look up across 3 JSON files to search ticket information. Basically,
 the more connections that are required, the more expensive the search. Also, the look up process
 should be recorded or cached. It can improve the search speed massively.

[ElasticSearch Reference](https://www.elastic.co/guide/en/elasticsearch/guide/current/relations.html)

---


## :tiger: Technology Stack
| Tech | Description | Learn more |
| --- | --- | --- |
| Circe | 	An functional JSON library. | [Circe](https://github.com/circe/circe) |
| scalatest | The Test library for Scala | [ScalaTest](http://www.scalatest.org) |
