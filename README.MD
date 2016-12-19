## org.lappsgrid.illinoisnlp
This repository includes NLP tools from the University of Illinois at Urbana-Champaign. The tools include a Chunker, a Lemmatizer, a Named Entity Recognizer, a Part of Speech Tagger, a Sentence Segmenter, and a Token Segmenter.

# Input
Each tool requires a LAPPS Grid Data object with the discriminator: [Discriminators.Uri.TEXT](http://vocab.lappsgrid.org/ns/media/text) and the text in the payload. There are no parameters for any of the tools.
 
# Output
The output will be a Data object in the LAPPS Interchange format ([LIF](http://vocab.lappsgrid.org/ns/media/jsonld#lif)).

### Chunker
The Chunker creates annotations of the type [chunk](http://vocab.lappsgrid.org/Chunk) for each chunk.

<details>
<summary> Example Output </summary>
```json
{
  "discriminator" : "http://vocab.lappsgrid.org/ns/media/jsonld#lif",
  "payload" : {
    "@context" : "http://vocab.lappsgrid.org/context-1.0.0.jsonld",
    "metadata" : { },
    "text" : {
      "@value" : "So I wilted right down on to the planks then, and give up; and it was all I could do to keep from crying."
    },
    "views" : [ {
      "metadata" : {
        "contains" : {
          "http://vocab.lappsgrid.org/Chunk" : {
            "producer" : "org.lappsgrid.illinoisnlp.Chunker",
            "type" : "chunks"
          }
        }
      },
      "annotations" : [ {
        "id" : "chunk0",
        "start" : 0,
        "end" : 2,
        "@type" : "http://vocab.lappsgrid.org/Chunk",
        "label" : "Chunk",
        "features" : {
          "http://vocab.lappsgrid.org/ns/media/text" : "So",
          "chunk type" : ""
        }
      }, {
        "id" : "chunk1",
        "start" : 3,
        "end" : 4,
        "@type" : "http://vocab.lappsgrid.org/Chunk",
        "label" : "Chunk",
        "features" : {
          "http://vocab.lappsgrid.org/ns/media/text" : "I",
          "chunk type" : "NP"
        }
      }, {
        "id" : "chunk2",
        "start" : 5,
        "end" : 11,
        "@type" : "http://vocab.lappsgrid.org/Chunk",
        "label" : "Chunk",
        "features" : {
          "http://vocab.lappsgrid.org/ns/media/text" : "wilted",
          "chunk type" : "VP"
        }
      }, {
        "id" : "chunk3",
        "start" : 12,
        "end" : 22,
        "@type" : "http://vocab.lappsgrid.org/Chunk",
        "label" : "Chunk",
        "features" : {
          "http://vocab.lappsgrid.org/ns/media/text" : "right down",
          "chunk type" : "ADVP"
        }
      }, {
        "id" : "chunk4",
        "start" : 23,
        "end" : 25,
        "@type" : "http://vocab.lappsgrid.org/Chunk",
        "label" : "Chunk",
        "features" : {
          "http://vocab.lappsgrid.org/ns/media/text" : "on",
          "chunk type" : "PP"
        }
      }, {
        "id" : "chunk5",
        "start" : 26,
        "end" : 28,
        "@type" : "http://vocab.lappsgrid.org/Chunk",
        "label" : "Chunk",
        "features" : {
          "http://vocab.lappsgrid.org/ns/media/text" : "to",
          "chunk type" : "PP"
        }
      }, {
        "id" : "chunk6",
        "start" : 29,
        "end" : 39,
        "@type" : "http://vocab.lappsgrid.org/Chunk",
        "label" : "Chunk",
        "features" : {
          "http://vocab.lappsgrid.org/ns/media/text" : "the planks",
          "chunk type" : "NP"
        }
      }, {
        "id" : "chunk7",
        "start" : 40,
        "end" : 44,
        "@type" : "http://vocab.lappsgrid.org/Chunk",
        "label" : "Chunk",
        "features" : {
          "http://vocab.lappsgrid.org/ns/media/text" : "then",
          "chunk type" : "ADVP"
        }
      }, {
        "id" : "chunk8",
        "start" : 50,
        "end" : 54,
        "@type" : "http://vocab.lappsgrid.org/Chunk",
        "label" : "Chunk",
        "features" : {
          "http://vocab.lappsgrid.org/ns/media/text" : "give",
          "chunk type" : "VP"
        }
      }, {
        "id" : "chunk9",
        "start" : 55,
        "end" : 57,
        "@type" : "http://vocab.lappsgrid.org/Chunk",
        "label" : "Chunk",
        "features" : {
          "http://vocab.lappsgrid.org/ns/media/text" : "up",
          "chunk type" : "PRT"
        }
      }, {
        "id" : "chunk10",
        "start" : 63,
        "end" : 65,
        "@type" : "http://vocab.lappsgrid.org/Chunk",
        "label" : "Chunk",
        "features" : {
          "http://vocab.lappsgrid.org/ns/media/text" : "it",
          "chunk type" : "NP"
        }
      }, {
        "id" : "chunk11",
        "start" : 66,
        "end" : 69,
        "@type" : "http://vocab.lappsgrid.org/Chunk",
        "label" : "Chunk",
        "features" : {
          "http://vocab.lappsgrid.org/ns/media/text" : "was",
          "chunk type" : "VP"
        }
      }, {
        "id" : "chunk12",
        "start" : 70,
        "end" : 75,
        "@type" : "http://vocab.lappsgrid.org/Chunk",
        "label" : "Chunk",
        "features" : {
          "http://vocab.lappsgrid.org/ns/media/text" : "all I",
          "chunk type" : "NP"
        }
      }, {
        "id" : "chunk13",
        "start" : 76,
        "end" : 92,
        "@type" : "http://vocab.lappsgrid.org/Chunk",
        "label" : "Chunk",
        "features" : {
          "http://vocab.lappsgrid.org/ns/media/text" : "could do to keep",
          "chunk type" : "VP"
        }
      }, {
        "id" : "chunk14",
        "start" : 93,
        "end" : 97,
        "@type" : "http://vocab.lappsgrid.org/Chunk",
        "label" : "Chunk",
        "features" : {
          "http://vocab.lappsgrid.org/ns/media/text" : "from",
          "chunk type" : "PP"
        }
      }, {
        "id" : "chunk15",
        "start" : 98,
        "end" : 104,
        "@type" : "http://vocab.lappsgrid.org/Chunk",
        "label" : "Chunk",
        "features" : {
          "http://vocab.lappsgrid.org/ns/media/text" : "crying",
          "chunk type" : "NP"
        }
      } ]
    } ]
  },
  "parameters" : { }
}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
```
</details>

### Lemmatizer
The Lemmatizer creates [Token](http://vocab.lappsgrid.org/Token) annotations for each token with the word and lemma as features along with the starting and ending positions of the token.
<details>
<summary> Example Output </summary>
````json
{
  "discriminator" : "http://vocab.lappsgrid.org/ns/media/jsonld#lif",
  "payload" : {
    "@context" : "http://vocab.lappsgrid.org/context-1.0.0.jsonld",
    "metadata" : { },
    "text" : {
      "@value" : "They could be running if they wanted to, but he wants their songs."
    },
    "views" : [ {
      "metadata" : {
        "contains" : {
          "http://vocab.lappsgrid.org/Token#lemma" : {
            "producer" : "org.lappsgrid.illinoisnlp.Lemmatizer",
            "type" : "lemma:uiuc"
          }
        }
      },
      "annotations" : [ {
        "id" : "tok0",
        "start" : 0,
        "end" : 3,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "token",
        "features" : {
          "word" : "They",
          "lemma" : "they"
        }
      }, {
        "id" : "tok1",
        "start" : 5,
        "end" : 9,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "token",
        "features" : {
          "word" : "could",
          "lemma" : "could"
        }
      }, {
        "id" : "tok2",
        "start" : 11,
        "end" : 12,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "token",
        "features" : {
          "word" : "be",
          "lemma" : "be"
        }
      }, {
        "id" : "tok3",
        "start" : 14,
        "end" : 20,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "token",
        "features" : {
          "word" : "running",
          "lemma" : "run"
        }
      }, {
        "id" : "tok4",
        "start" : 22,
        "end" : 23,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "token",
        "features" : {
          "word" : "if",
          "lemma" : "if"
        }
      }, {
        "id" : "tok5",
        "start" : 25,
        "end" : 28,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "token",
        "features" : {
          "word" : "they",
          "lemma" : "they"
        }
      }, {
        "id" : "tok6",
        "start" : 30,
        "end" : 35,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "token",
        "features" : {
          "word" : "wanted",
          "lemma" : "want"
        }
      }, {
        "id" : "tok7",
        "start" : 37,
        "end" : 38,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "token",
        "features" : {
          "word" : "to",
          "lemma" : "to"
        }
      }, {
        "id" : "tok8",
        "start" : 39,
        "end" : 39,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "token",
        "features" : {
          "word" : ",",
          "lemma" : ","
        }
      }, {
        "id" : "tok9",
        "start" : 41,
        "end" : 43,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "token",
        "features" : {
          "word" : "but",
          "lemma" : "but"
        }
      }, {
        "id" : "tok10",
        "start" : 45,
        "end" : 46,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "token",
        "features" : {
          "word" : "he",
          "lemma" : "he"
        }
      }, {
        "id" : "tok11",
        "start" : 48,
        "end" : 52,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "token",
        "features" : {
          "word" : "wants",
          "lemma" : "want"
        }
      }, {
        "id" : "tok12",
        "start" : 54,
        "end" : 58,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "token",
        "features" : {
          "word" : "their",
          "lemma" : "their"
        }
      }, {
        "id" : "tok13",
        "start" : 60,
        "end" : 64,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "token",
        "features" : {
          "word" : "songs",
          "lemma" : "song"
        }
      }, {
        "id" : "tok14",
        "start" : 65,
        "end" : 65,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "token",
        "features" : {
          "word" : ".",
          "lemma" : "."
        }
      } ]
    } ]
  },
  "parameters" : { }
}
````
</details>

### Named Entity Recognizer
The Named Entity Recognizer produces annotations of the types [Date](http://vocab.lappsgrid.org/Date), [Location](http://vocab.lappsgrid.org/Location), [Organization](http://vocab.lappsgrid.org/Organization), [Person](http://vocab.lappsgrid.org/Person), or [Annotation](http://vocab.lappsgrid.org/Annotation.html) (if the named entity does not match the first 4) for each named entity depending on which type it is. It also includes the starting and ending positions of each named entity.
<details>
<summary> Example Output </summary>
````json
{
  "discriminator" : "http://vocab.lappsgrid.org/ns/media/jsonld#lif",
  "payload" : {
    "@context" : "http://vocab.lappsgrid.org/context-1.0.0.jsonld",
    "metadata" : { },
    "text" : {
      "@value" : "Michael Jeffrey Jordan (born February 17, 1963), also known by his initials, MJ, is an American retired professional basketball player. He is also a businessman, and principal owner and chairman of the Charlotte Hornets. Jordan played 15 seasons in the National Basketball Association (NBA) for the Chicago Bulls and Washington Wizards."
    },
    "views" : [ {
      "metadata" : {
        "contains" : {
          "http://vocab.lappsgrid.org/Token#pos" : {
            "producer" : "org.lappsgrid.illinoisnlp.NamedEntityRecognizer",
            "type" : "named-entities:uiuc"
          }
        }
      },
      "annotations" : [ {
        "id" : "namedentity0",
        "start" : 0,
        "end" : 21,
        "@type" : "http://vocab.lappsgrid.org/Person",
        "label" : "Named Entity"
      }, {
        "id" : "namedentity1",
        "start" : 29,
        "end" : 45,
        "@type" : "http://vocab.lappsgrid.org/Date",
        "label" : "Named Entity"
      }, {
        "id" : "namedentity2",
        "start" : 77,
        "end" : 78,
        "@type" : "http://vocab.lappsgrid.org/Person",
        "label" : "Named Entity"
      }, {
        "id" : "namedentity3",
        "start" : 87,
        "end" : 94,
        "@type" : "http://vocab.lappsgrid.org/Annotation",
        "label" : "Named Entity",
        "features" : {
          "category" : "NORP"
        }
      }, {
        "id" : "namedentity4",
        "start" : 198,
        "end" : 218,
        "@type" : "http://vocab.lappsgrid.org/Organization",
        "label" : "Named Entity"
      }, {
        "id" : "namedentity5",
        "start" : 221,
        "end" : 226,
        "@type" : "http://vocab.lappsgrid.org/Annotation",
        "label" : "Named Entity",
        "features" : {
          "category" : "GPE"
        }
      }, {
        "id" : "namedentity6",
        "start" : 235,
        "end" : 236,
        "@type" : "http://vocab.lappsgrid.org/Annotation",
        "label" : "Named Entity",
        "features" : {
          "category" : "CARDINAL"
        }
      }, {
        "id" : "namedentity7",
        "start" : 249,
        "end" : 283,
        "@type" : "http://vocab.lappsgrid.org/Organization",
        "label" : "Named Entity"
      }, {
        "id" : "namedentity8",
        "start" : 286,
        "end" : 288,
        "@type" : "http://vocab.lappsgrid.org/Organization",
        "label" : "Named Entity"
      }, {
        "id" : "namedentity9",
        "start" : 299,
        "end" : 311,
        "@type" : "http://vocab.lappsgrid.org/Organization",
        "label" : "Named Entity"
      }, {
        "id" : "namedentity10",
        "start" : 317,
        "end" : 334,
        "@type" : "http://vocab.lappsgrid.org/Organization",
        "label" : "Named Entity"
      } ]
    } ]
  },
  "parameters" : { }
}
````
</details>

### Part of Speech Tagger
<details>
<summary> Example Output </summary>
The Part of Speech Tagger creates [part of speech token](http://vocab.lappsgrid.org/Token#pos) annotations for each token with the starting and ending positions of the token.
````json
{
  "discriminator" : "http://vocab.lappsgrid.org/ns/media/jsonld#lif",
  "payload" : {
    "@context" : "http://vocab.lappsgrid.org/context-1.0.0.jsonld",
    "metadata" : { },
    "text" : {
      "@value" : "Don't count the days. Make the days count."
    },
    "views" : [ {
      "metadata" : {
        "contains" : {
          "http://vocab.lappsgrid.org/Token#pos" : {
            "producer" : "org.lappsgrid.illinoisnlp.POS",
            "type" : "pos:uiuc"
          }
        }
      },
      "annotations" : [ {
        "id" : "tok0",
        "start" : 0,
        "end" : 1,
        "@type" : "http://vocab.lappsgrid.org/Token#pos",
        "label" : "token",
        "features" : {
          "http://vocab.lappsgrid.org/Token" : "Do",
          "pos" : "VBP"
        }
      }, {
        "id" : "tok1",
        "start" : 2,
        "end" : 4,
        "@type" : "http://vocab.lappsgrid.org/Token#pos",
        "label" : "token",
        "features" : {
          "http://vocab.lappsgrid.org/Token" : "n't",
          "pos" : "RB"
        }
      }, {
        "id" : "tok2",
        "start" : 6,
        "end" : 10,
        "@type" : "http://vocab.lappsgrid.org/Token#pos",
        "label" : "token",
        "features" : {
          "http://vocab.lappsgrid.org/Token" : "count",
          "pos" : "VB"
        }
      }, {
        "id" : "tok3",
        "start" : 12,
        "end" : 14,
        "@type" : "http://vocab.lappsgrid.org/Token#pos",
        "label" : "token",
        "features" : {
          "http://vocab.lappsgrid.org/Token" : "the",
          "pos" : "DT"
        }
      }, {
        "id" : "tok4",
        "start" : 16,
        "end" : 19,
        "@type" : "http://vocab.lappsgrid.org/Token#pos",
        "label" : "token",
        "features" : {
          "http://vocab.lappsgrid.org/Token" : "days",
          "pos" : "NNS"
        }
      }, {
        "id" : "tok5",
        "start" : 20,
        "end" : 20,
        "@type" : "http://vocab.lappsgrid.org/Token#pos",
        "label" : "token",
        "features" : {
          "http://vocab.lappsgrid.org/Token" : ".",
          "pos" : "."
        }
      }, {
        "id" : "tok6",
        "start" : 22,
        "end" : 25,
        "@type" : "http://vocab.lappsgrid.org/Token#pos",
        "label" : "token",
        "features" : {
          "http://vocab.lappsgrid.org/Token" : "Make",
          "pos" : "VBP"
        }
      }, {
        "id" : "tok7",
        "start" : 27,
        "end" : 29,
        "@type" : "http://vocab.lappsgrid.org/Token#pos",
        "label" : "token",
        "features" : {
          "http://vocab.lappsgrid.org/Token" : "the",
          "pos" : "DT"
        }
      }, {
        "id" : "tok8",
        "start" : 31,
        "end" : 34,
        "@type" : "http://vocab.lappsgrid.org/Token#pos",
        "label" : "token",
        "features" : {
          "http://vocab.lappsgrid.org/Token" : "days",
          "pos" : "NNS"
        }
      }, {
        "id" : "tok9",
        "start" : 36,
        "end" : 40,
        "@type" : "http://vocab.lappsgrid.org/Token#pos",
        "label" : "token",
        "features" : {
          "http://vocab.lappsgrid.org/Token" : "count",
          "pos" : "VBP"
        }
      }, {
        "id" : "tok10",
        "start" : 41,
        "end" : 41,
        "@type" : "http://vocab.lappsgrid.org/Token#pos",
        "label" : "token",
        "features" : {
          "http://vocab.lappsgrid.org/Token" : ".",
          "pos" : "."
        }
      } ]
    } ]
  },
  "parameters" : { }
}
````
</details>

### Sentence Segmenter
The Sentence Segmenter produces [Sentence](http://vocab.lappsgrid.org/Sentence) annotations for each sentence.
<details>
<summary> Example Output </summary>
```json
{
  "discriminator" : "http://vocab.lappsgrid.org/ns/media/jsonld#lif",
  "payload" : {
    "@context" : "http://vocab.lappsgrid.org/context-1.0.0.jsonld",
    "metadata" : { },
    "text" : {
      "@value" : "A fearful man, all in coarse gray, with a great iron on his leg. A man with no hat, and with broken shoes, and with an old rag tied round his head. A man who had been soaked in water, and smothered in mud, and lamed by stones, and cut by flints, and stung by nettles, and torn by briars; who limped, and shivered, and glared, and growled; and whose teeth chattered in his head as he seized me by the chin.\n\"Oh! Don't cut my throat, sir,\" I pleaded in terror. \"Pray don't do it, sir.\"\n\"Tell us your name!\" said the man. \"Quick!\"\n\"Pip, sir.\"\n\"Once more,\" said the man, staring at me. \"Give it mouth!\"\n\"Pip. Pip, sir.\"\n\"Show us where you live,\" said the man. \"Pint out the place!\""
    },
    "views" : [ {
      "metadata" : {
        "contains" : {
          "http://vocab.lappsgrid.org/Sentence" : {
            "producer" : "org.lappsgrid.illinoisnlp.SentenceSegmenter",
            "type" : "sentence:uiuc"
          }
        }
      },
      "annotations" : [ {
        "id" : "sentence0",
        "start" : 0,
        "end" : 17,
        "@type" : "http://vocab.lappsgrid.org/Sentence",
        "label" : "Sentence"
      }, {
        "id" : "sentence1",
        "start" : 17,
        "end" : 38,
        "@type" : "http://vocab.lappsgrid.org/Sentence",
        "label" : "Sentence"
      }, {
        "id" : "sentence2",
        "start" : 38,
        "end" : 99,
        "@type" : "http://vocab.lappsgrid.org/Sentence",
        "label" : "Sentence"
      }, {
        "id" : "sentence3",
        "start" : 99,
        "end" : 102,
        "@type" : "http://vocab.lappsgrid.org/Sentence",
        "label" : "Sentence"
      }, {
        "id" : "sentence4",
        "start" : 102,
        "end" : 116,
        "@type" : "http://vocab.lappsgrid.org/Sentence",
        "label" : "Sentence"
      }, {
        "id" : "sentence5",
        "start" : 116,
        "end" : 126,
        "@type" : "http://vocab.lappsgrid.org/Sentence",
        "label" : "Sentence"
      }, {
        "id" : "sentence6",
        "start" : 126,
        "end" : 137,
        "@type" : "http://vocab.lappsgrid.org/Sentence",
        "label" : "Sentence"
      }, {
        "id" : "sentence7",
        "start" : 137,
        "end" : 141,
        "@type" : "http://vocab.lappsgrid.org/Sentence",
        "label" : "Sentence"
      }, {
        "id" : "sentence8",
        "start" : 141,
        "end" : 147,
        "@type" : "http://vocab.lappsgrid.org/Sentence",
        "label" : "Sentence"
      }, {
        "id" : "sentence9",
        "start" : 147,
        "end" : 160,
        "@type" : "http://vocab.lappsgrid.org/Sentence",
        "label" : "Sentence"
      }, {
        "id" : "sentence10",
        "start" : 160,
        "end" : 166,
        "@type" : "http://vocab.lappsgrid.org/Sentence",
        "label" : "Sentence"
      }, {
        "id" : "sentence11",
        "start" : 166,
        "end" : 173,
        "@type" : "http://vocab.lappsgrid.org/Sentence",
        "label" : "Sentence"
      }, {
        "id" : "sentence12",
        "start" : 173,
        "end" : 185,
        "@type" : "http://vocab.lappsgrid.org/Sentence",
        "label" : "Sentence"
      }, {
        "id" : "sentence13",
        "start" : 185,
        "end" : 192,
        "@type" : "http://vocab.lappsgrid.org/Sentence",
        "label" : "Sentence"
      } ]
    } ]
  },
  "parameters" : { }
}
```
</details>

### Token Segmenter
The Token Segmenter produces a [Token](http://vocab.lappsgrid.org/Token) annotation for each token along with their starting and ending positions.
<details>
<summary> Example Output </summary>
````json 
{
  "discriminator" : "http://vocab.lappsgrid.org/ns/media/jsonld#lif",
  "payload" : {
    "@context" : "http://vocab.lappsgrid.org/context-1.0.0.jsonld",
    "metadata" : { },
    "text" : {
      "@value" : "Miss Watson would say, \"Don't put your feet up there, Huckleberry;\" and \"Don't scrunch up like that, Huckleberry—set up straight;\" and pretty soon she would say, \"Don't gap and stretch like that, Huckleberry—why don't you try to behave?\" "
    },
    "views" : [ {
      "metadata" : {
        "contains" : {
          "http://vocab.lappsgrid.org/Token" : {
            "producer" : "org.lappsgrid.illinoisnlp.TokenSegmenter",
            "type" : "tokens"
          }
        }
      },
      "annotations" : [ {
        "id" : "tok0",
        "start" : 0,
        "end" : 3,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "Miss"
        }
      }, {
        "id" : "tok1",
        "start" : 5,
        "end" : 10,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "Watson"
        }
      }, {
        "id" : "tok2",
        "start" : 12,
        "end" : 16,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "would"
        }
      }, {
        "id" : "tok3",
        "start" : 18,
        "end" : 20,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "say"
        }
      }, {
        "id" : "tok4",
        "start" : 21,
        "end" : 21,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : ","
        }
      }, {
        "id" : "tok5",
        "start" : 23,
        "end" : 23,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "\""
        }
      }, {
        "id" : "tok6",
        "start" : 24,
        "end" : 25,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "Do"
        }
      }, {
        "id" : "tok7",
        "start" : 26,
        "end" : 28,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "n't"
        }
      }, {
        "id" : "tok8",
        "start" : 30,
        "end" : 32,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "put"
        }
      }, {
        "id" : "tok9",
        "start" : 34,
        "end" : 37,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "your"
        }
      }, {
        "id" : "tok10",
        "start" : 39,
        "end" : 42,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "feet"
        }
      }, {
        "id" : "tok11",
        "start" : 44,
        "end" : 45,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "up"
        }
      }, {
        "id" : "tok12",
        "start" : 47,
        "end" : 51,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "there"
        }
      }, {
        "id" : "tok13",
        "start" : 52,
        "end" : 52,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : ","
        }
      }, {
        "id" : "tok14",
        "start" : 54,
        "end" : 64,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "Huckleberry"
        }
      }, {
        "id" : "tok15",
        "start" : 65,
        "end" : 65,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : ";"
        }
      }, {
        "id" : "tok16",
        "start" : 66,
        "end" : 66,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "\""
        }
      }, {
        "id" : "tok17",
        "start" : 68,
        "end" : 70,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "and"
        }
      }, {
        "id" : "tok18",
        "start" : 72,
        "end" : 72,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "\""
        }
      }, {
        "id" : "tok19",
        "start" : 73,
        "end" : 74,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "Do"
        }
      }, {
        "id" : "tok20",
        "start" : 75,
        "end" : 77,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "n't"
        }
      }, {
        "id" : "tok21",
        "start" : 79,
        "end" : 85,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "scrunch"
        }
      }, {
        "id" : "tok22",
        "start" : 87,
        "end" : 88,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "up"
        }
      }, {
        "id" : "tok23",
        "start" : 90,
        "end" : 93,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "like"
        }
      }, {
        "id" : "tok24",
        "start" : 95,
        "end" : 98,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "that"
        }
      }, {
        "id" : "tok25",
        "start" : 99,
        "end" : 99,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : ","
        }
      }, {
        "id" : "tok26",
        "start" : 101,
        "end" : 111,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "Huckleberry"
        }
      }, {
        "id" : "tok27",
        "start" : 112,
        "end" : 112,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "—"
        }
      }, {
        "id" : "tok28",
        "start" : 113,
        "end" : 115,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "set"
        }
      }, {
        "id" : "tok29",
        "start" : 117,
        "end" : 118,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "up"
        }
      }, {
        "id" : "tok30",
        "start" : 120,
        "end" : 127,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "straight"
        }
      }, {
        "id" : "tok31",
        "start" : 128,
        "end" : 128,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : ";"
        }
      }, {
        "id" : "tok32",
        "start" : 129,
        "end" : 129,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "\""
        }
      }, {
        "id" : "tok33",
        "start" : 131,
        "end" : 133,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "and"
        }
      }, {
        "id" : "tok34",
        "start" : 135,
        "end" : 140,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "pretty"
        }
      }, {
        "id" : "tok35",
        "start" : 142,
        "end" : 145,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "soon"
        }
      }, {
        "id" : "tok36",
        "start" : 147,
        "end" : 149,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "she"
        }
      }, {
        "id" : "tok37",
        "start" : 151,
        "end" : 155,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "would"
        }
      }, {
        "id" : "tok38",
        "start" : 157,
        "end" : 159,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "say"
        }
      }, {
        "id" : "tok39",
        "start" : 160,
        "end" : 160,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : ","
        }
      }, {
        "id" : "tok40",
        "start" : 162,
        "end" : 162,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "\""
        }
      }, {
        "id" : "tok41",
        "start" : 163,
        "end" : 164,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "Do"
        }
      }, {
        "id" : "tok42",
        "start" : 165,
        "end" : 167,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "n't"
        }
      }, {
        "id" : "tok43",
        "start" : 169,
        "end" : 171,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "gap"
        }
      }, {
        "id" : "tok44",
        "start" : 173,
        "end" : 175,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "and"
        }
      }, {
        "id" : "tok45",
        "start" : 177,
        "end" : 183,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "stretch"
        }
      }, {
        "id" : "tok46",
        "start" : 185,
        "end" : 188,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "like"
        }
      }, {
        "id" : "tok47",
        "start" : 190,
        "end" : 193,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "that"
        }
      }, {
        "id" : "tok48",
        "start" : 194,
        "end" : 194,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : ","
        }
      }, {
        "id" : "tok49",
        "start" : 196,
        "end" : 206,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "Huckleberry"
        }
      }, {
        "id" : "tok50",
        "start" : 207,
        "end" : 207,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "—"
        }
      }, {
        "id" : "tok51",
        "start" : 208,
        "end" : 210,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "why"
        }
      }, {
        "id" : "tok52",
        "start" : 212,
        "end" : 213,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "do"
        }
      }, {
        "id" : "tok53",
        "start" : 214,
        "end" : 216,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "n't"
        }
      }, {
        "id" : "tok54",
        "start" : 218,
        "end" : 220,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "you"
        }
      }, {
        "id" : "tok55",
        "start" : 222,
        "end" : 224,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "try"
        }
      }, {
        "id" : "tok56",
        "start" : 226,
        "end" : 227,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "to"
        }
      }, {
        "id" : "tok57",
        "start" : 229,
        "end" : 234,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "behave"
        }
      }, {
        "id" : "tok58",
        "start" : 235,
        "end" : 235,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "?"
        }
      }, {
        "id" : "tok59",
        "start" : 236,
        "end" : 236,
        "@type" : "http://vocab.lappsgrid.org/Token",
        "label" : "http://vocab.lappsgrid.org/Token",
        "features" : {
          "word" : "\""
        }
      } ]
    } ]
  },
  "parameters" : { }
}
````
</details>