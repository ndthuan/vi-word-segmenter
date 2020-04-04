# Word Segmentation Service

HTTP wrapper of the excellent VnCoreNLP library.

# Build Instruction

* Clone https://github.com/ndthuan/VnCoreNLP - it's the forked version of the library
* cd into VnCoreNLP and run `mvn install` to compile and install it locally
* Go back to this repo, run `docker-build.sh` to build a Docker image.

# Docker

Prebuilt Docker images can be found at https://hub.docker.com/r/ndthuan/vi-word-segmenter.

# API Usage Examples

## Segmentation without tagging

Set `skipPunct=0` or remove it to include punctuations.

```shell script
curl -s -H 'Content-Type: application/json' -XPOST -d'{"text": "\"Bệnh nhân 83\", nữ, 50 tuổi, quốc tịch Mỹ trú tại Bình Thạnh, TP HCM. Trong 14 ngày trước nhập cảnh, bệnh nhân đi Phuket, Thái Lan và từ Istanbul, Thổ Nhĩ Kỳ về TP HCM ngày 15/3 và xét nghiệm dương tính. Bệnh nhân âm tính 3 lần liên tiếp trong 3 ngày 31/3 đến 2/4."}' 'http://localhost:8080/v1/segmenting?skipPunct=1' | jq



{
  "sentences": [
    "Bệnh_nhân 83 nữ 50 tuổi quốc_tịch Mỹ trú tại Bình_Thạnh TP HCM",
    "Trong 14 ngày trước nhập_cảnh bệnh_nhân đi Phuket Thái_Lan và từ Istanbul Thổ_Nhĩ_Kỳ về TP HCM ngày 15/3 và xét_nghiệm dương_tính",
    "Bệnh_nhân âm_tính 3 lần liên_tiếp trong 3 ngày 31/3 đến 2/4"
  ]
}
```

## Segmentation with POS/NER/DEP tagging

```shell script
curl -s -H 'Content-Type: application/json' -XPOST -d'{"text": "\"Bệnh nhân 83\", nữ, 50 tuổi, quốc tịch Mỹ trú tại Bình Thạnh, TP HCM. Trong 14 ngày trước nhập cảnh, bệnh nhân đi Phuket, Thái Lan và từ Istanbul, Thổ Nhĩ Kỳ về TP HCM ngày 15/3 và xét nghiệm dương tính. Bệnh nhân âm tính 3 lần liên tiếp trong 3 ngày 31/3 đến 2/4."}' 'http://localhost:8080/v1/tagging' | jq



{
  "sentences": [
    [
      {
        "form": "\"",
        "pos": "CH",
        "ner": "O",
        "dep": "punct"
      },
      {
        "form": "Bệnh_nhân",
        "pos": "N",
        "ner": "O",
        "dep": "sub"
      },
      {
        "form": "83",
        "pos": "M",
        "ner": "O",
        "dep": "det"
      },
      {
        "form": "\"",
        "pos": "CH",
        "ner": "O",
        "dep": "punct"
      },
      {
        "form": ",",
        "pos": "CH",
        "ner": "O",
        "dep": "punct"
      },
      {
        "form": "nữ",
        "pos": "N",
        "ner": "O",
        "dep": "nmod"
      },
      {
        "form": ",",
        "pos": "CH",
        "ner": "O",
        "dep": "punct"
      },
      {
        "form": "50",
        "pos": "M",
        "ner": "O",
        "dep": "det"
      },
      {
        "form": "tuổi",
        "pos": "N",
        "ner": "O",
        "dep": "nmod"
      },
      {
        "form": ",",
        "pos": "CH",
        "ner": "O",
        "dep": "punct"
      },
      {
        "form": "quốc_tịch",
        "pos": "N",
        "ner": "O",
        "dep": "nmod"
      },
      {
        "form": "Mỹ",
        "pos": "Np",
        "ner": "B-LOC",
        "dep": "nmod"
      },
      {
        "form": "trú",
        "pos": "V",
        "ner": "O",
        "dep": "root"
      },
      {
        "form": "tại",
        "pos": "E",
        "ner": "O",
        "dep": "loc"
      },
      {
        "form": "Bình_Thạnh",
        "pos": "Np",
        "ner": "B-LOC",
        "dep": "pob"
      },
      {
        "form": ",",
        "pos": "CH",
        "ner": "O",
        "dep": "punct"
      },
      {
        "form": "TP",
        "pos": "Ny",
        "ner": "B-LOC",
        "dep": "pob"
      },
      {
        "form": "HCM",
        "pos": "Ny",
        "ner": "I-LOC",
        "dep": "nmod"
      },
      {
        "form": ".",
        "pos": "CH",
        "ner": "O",
        "dep": "punct"
      }
    ],
    [
      {
        "form": "Trong",
        "pos": "E",
        "ner": "O",
        "dep": "tmp"
      },
      {
        "form": "14",
        "pos": "M",
        "ner": "O",
        "dep": "det"
      },
      {
        "form": "ngày",
        "pos": "N",
        "ner": "O",
        "dep": "pob"
      },
      {
        "form": "trước",
        "pos": "A",
        "ner": "O",
        "dep": "nmod"
      },
      {
        "form": "nhập_cảnh",
        "pos": "V",
        "ner": "O",
        "dep": "nmod"
      },
      {
        "form": ",",
        "pos": "CH",
        "ner": "O",
        "dep": "punct"
      },
      {
        "form": "bệnh_nhân",
        "pos": "N",
        "ner": "O",
        "dep": "sub"
      },
      {
        "form": "đi",
        "pos": "V",
        "ner": "O",
        "dep": "root"
      },
      {
        "form": "Phuket",
        "pos": "Np",
        "ner": "B-LOC",
        "dep": "loc"
      },
      {
        "form": ",",
        "pos": "CH",
        "ner": "O",
        "dep": "punct"
      },
      {
        "form": "Thái_Lan",
        "pos": "Np",
        "ner": "B-LOC",
        "dep": "nmod"
      },
      {
        "form": "và",
        "pos": "Cc",
        "ner": "O",
        "dep": "coord"
      },
      {
        "form": "từ",
        "pos": "E",
        "ner": "O",
        "dep": "loc"
      },
      {
        "form": "Istanbul",
        "pos": "Np",
        "ner": "B-LOC",
        "dep": "pob"
      },
      {
        "form": ",",
        "pos": "CH",
        "ner": "O",
        "dep": "punct"
      },
      {
        "form": "Thổ_Nhĩ_Kỳ",
        "pos": "Np",
        "ner": "B-LOC",
        "dep": "nmod"
      },
      {
        "form": "về",
        "pos": "E",
        "ner": "O",
        "dep": "pmod"
      },
      {
        "form": "TP",
        "pos": "Ny",
        "ner": "B-LOC",
        "dep": "pob"
      },
      {
        "form": "HCM",
        "pos": "Ny",
        "ner": "I-LOC",
        "dep": "nmod"
      },
      {
        "form": "ngày",
        "pos": "N",
        "ner": "O",
        "dep": "pob"
      },
      {
        "form": "15/3",
        "pos": "M",
        "ner": "O",
        "dep": "det"
      },
      {
        "form": "và",
        "pos": "Cc",
        "ner": "O",
        "dep": "coord"
      },
      {
        "form": "xét_nghiệm",
        "pos": "V",
        "ner": "O",
        "dep": "conj"
      },
      {
        "form": "dương_tính",
        "pos": "A",
        "ner": "O",
        "dep": "vmod"
      },
      {
        "form": ".",
        "pos": "CH",
        "ner": "O",
        "dep": "punct"
      }
    ],
    [
      {
        "form": "Bệnh_nhân",
        "pos": "N",
        "ner": "O",
        "dep": "root"
      },
      {
        "form": "âm_tính",
        "pos": "N",
        "ner": "O",
        "dep": "nmod"
      },
      {
        "form": "3",
        "pos": "M",
        "ner": "O",
        "dep": "det"
      },
      {
        "form": "lần",
        "pos": "N",
        "ner": "O",
        "dep": "nmod"
      },
      {
        "form": "liên_tiếp",
        "pos": "A",
        "ner": "O",
        "dep": "nmod"
      },
      {
        "form": "trong",
        "pos": "E",
        "ner": "O",
        "dep": "tmp"
      },
      {
        "form": "3",
        "pos": "M",
        "ner": "O",
        "dep": "det"
      },
      {
        "form": "ngày",
        "pos": "N",
        "ner": "O",
        "dep": "pob"
      },
      {
        "form": "31/3",
        "pos": "M",
        "ner": "O",
        "dep": "det"
      },
      {
        "form": "đến",
        "pos": "E",
        "ner": "O",
        "dep": "pmod"
      },
      {
        "form": "2/4",
        "pos": "M",
        "ner": "O",
        "dep": "dep"
      },
      {
        "form": ".",
        "pos": "CH",
        "ner": "O",
        "dep": "punct"
      }
    ]
  ]
}
```
