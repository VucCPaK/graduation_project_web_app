<template>
  <div class="container-sm">
    <fieldset>
      <legend> {{ this.$route.params.id }}</legend>
      <div class="mb-3">
        <label class="form-label">Amount: </label>
        <input v-model="item.amount" type="number" class="form-control">
      </div>
      <div class="mb-3">
        <label class="form-label">Categories: </label>
        <input v-model="item.categories" type="text" class="form-control">
      </div>
      <div class="mb-3">
        <label class="form-label">Description: </label>
        <input v-model="item.description" type="text" class="form-control">
      </div>
      <div class="mb-3">
        <label class="form-label">Name:</label>
        <input v-model="item.name" type="text" class="form-control">
      </div>
      <div class="mb-3">
        <label class="form-label">Price:</label>
        <input v-model="item.price" type="number" class="form-control">
      </div>
      <div class="mb-3">
        <label class="form-label">Type:</label>
        <input v-model="item.type" type="text" class="form-control">
      </div>
      <div class="mb-3">
        <label class="form-label">Add images:</label>
        <input type="file" multiple ref="files" v-on:change="handleFilesUpload()" class="form-control"/>
      </div>
      <div class="mb-3">
        <ul class="slides images-scrollbar wrapper">
          <li v-for="img in item.pictures">
            <img class="indent spec-img" :src="getPictureUrl(img)" alt="..."/>
            <button class="btn-cls" v-on:click="removeFromPictures(img)">X</button>
            <div class="justify-content-center" style="width: 128px; height: 26px">
              <button class="button-prev" v-on:click="leftSwap(img)">
                <
              </button>
              <button class="button-next" v-on:click="rightSwap(img)">
                >
              </button>
            </div>
          </li>
        </ul>
      </div>
      <div class="mb-3">
        <button v-on:click="submit()" class="btn btn-primary">Confirm</button>
      </div>
    </fieldset>
  </div>
</template>

<script>
import supplyService from "../../services/supplyService.js";
import shopService from "../../services/shopService.js";

export default {
  name: "supply",
  data() {
    return {
      item: {},
      files: '',
      picturesToDelete: []
    }
  },

  beforeCreate() {
    shopService
        .getItemById(this.$route.params.id)
        .then((item) => this.item = item)
  },

  methods: {
    handleFilesUpload() {
      this.files = this.$refs.files.files;
    },

    getPictureUrl(itemEPicture) {
      return shopService.getPictureUrl(itemEPicture);
    },

    submit() {
      let formData = new FormData();

      for (let i = 0; i < this.files.length; i++) {
        let file = this.files[i];

        formData.append('files', file);
      }

      supplyService.updateItem(this.item.id, this.item).then(() => {
        if (this.files.length !== 0)
          supplyService.addImages(this.item.id, formData);

        if (this.picturesToDelete.length !== 0)
          supplyService.removeImages(this.picturesToDelete);

        supplyService.changePriorities(this.item.id, this.item.pictures);
      }).then(() => this.$router.push(`/shop`));

    },

    // work with pictures
    leftSwap(img) {
      let imgIndex = this.item.pictures.indexOf(img);

      if (imgIndex === 0) {
        console.log("err");
        return;
      }

      let temp = this.item.pictures[imgIndex - 1];
      this.item.pictures[imgIndex - 1] = img;
      this.item.pictures[imgIndex] = temp;

      this.$forceUpdate();
    },

    rightSwap(img) {
      let imgIndex = this.item.pictures.indexOf(img);

      if (imgIndex === this.item.pictures.length - 1) {
        console.log("err");
        return;
      }

      let temp = this.item.pictures[imgIndex + 1];
      this.item.pictures[imgIndex + 1] = img;
      this.item.pictures[imgIndex] = temp;

      this.$forceUpdate();
    },

    removeFromPictures(img) {
      this.item.pictures = this.item.pictures.filter(picId => picId !== img);
      this.picturesToDelete.push(img);
    },
  }
}

</script>

<style scoped>
@import "~bootstrap/dist/css/bootstrap.min.css";

.wrapper {
  width: auto;
  overflow-x: scroll;
  white-space: nowrap;
}

.images-scrollbar {
  display: flex;
}

.button-prev {
  height: auto;
  margin-left: 40px;
}

.button-next {
  height: auto;
}

.slides {
  margin: 0;
  padding-top: 10px;
  list-style: none;
}

.indent {
  margin-bottom: 8px;
  margin-right: 8px;
  margin-top: 8px;
  border: 2px #5a6268;
}

.spec-img {
  object-fit: contain;
  width: 120px;
  height: 120px;
}

.btn-cls {
  position: absolute;
  transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  color: red;
  border-radius: 5px;
}
</style>