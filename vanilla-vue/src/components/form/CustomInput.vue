<template>
  <div>
     <input class="form-control"
        ref="CustomInput"
        :type="type"
        :disabled="disabled"
        v-model="value"
        :class="{'is-invalid': isInvalid}"
      />
      <div class="invalid-feedback" v-if="isInvalid">{{ inValidMessage }}</div>
  </div>
</template>

<script>
export default {
  props: {
    type: {
      type: String,
      default: 'text'
    },
    disabled: {
      type: Boolean,
      default: false
    },
    nullable: {
      type: Boolean,
      default: true
    },
    nullableMessage: {
      type: String,
      default: ''
    },
    ip: {
      type: Boolean,
      default: false
    },
    ipMessage: {
      type: String,
      default: ''
    },
    onlynumber: {
      type: Boolean,
      default: false
    },
    onlynumberMessage: {
      type: String,
      default: ''
    },
    data: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      isInvalid: false,
      inValidMessage: '',
      value: ''
    }
  },
  created() {
    this.value = this.data
  },
  watch: {
    data() {
      this.value = this.data
    }
  },
  methods: {
    valid() {
      this.isInvalid = false
      this.inValidMessage = ''

      if (!this.nullable) {
        if (!this.value || this.value.trim() === '') {
          this.isInvalid = true
          this.inValidMessage = this.nullableMessage
          return false
        }
      }

      if (this.ip) {
        if (this.value && this.value.trim() !== '') {
          const regular = /^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/

          if (!regular.test(this.value)) {
            this.isInvalid = true
            this.inValidMessage = this.ipMessage
            return false
          }
        }
      }

      if (this.onlynumber) {
        if (this.value && this.value.trim() !== '') {
          const regular = /^[0-9]+$/

          if (!regular.test(this.value)) {
            this.isInvalid = true
            this.inValidMessage = this.onlynumberMessage
            return false
          }
        }
      }

      return true
    }
  }
}
</script>