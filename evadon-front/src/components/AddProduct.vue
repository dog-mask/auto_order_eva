<template>
  <div>
    <b-form @submit="onSubmit" @reset="onReset">
      <b-form-group id="input-group-1" label="상품명:" label-for="input-1">
        
        <b-form-input
          id="input-1"
          v-model="form.name"
          type="상품명"
          placeholder="상품명"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="상품코드:" label-for="input-2">
        
        <b-form-input
          id="input-2"
          v-model="form.code"
          placeholder="상품코드"
          required
        ></b-form-input>
      </b-form-group>

      <b-button type="submit" variant="primary">추가</b-button>
      <b-button type="reset" variant="danger">재입력</b-button>
    </b-form>
  </div>
</template>

<script>
import {addProduct} from '../service'
  export default {
    data() {
      return {
        form: {
          name: '',
          code: '',
        },
      }
    },
    methods: {
      onSubmit(event) {
          event.preventDefault()
          const newItem = {
              name : this.form.name,
              code : this.form.code
          }
        addProduct(newItem)
        this.$router.replace("/")
      },
      onReset(event) {
        event.preventDefault()
        // Reset our form values
        this.form.name = ''
        this.form.code = ''
      }
    }
  }
</script>