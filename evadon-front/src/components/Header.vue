<template>
    <div>
  <b-navbar toggleable="lg" type="dark" variant="info">
    <b-navbar-brand to="/">Eva-auto</b-navbar-brand>

    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

    <b-collapse id="nav-collapse" is-nav>
      <b-navbar-nav>
        <b-nav-item to="/hall">홀</b-nav-item>
        <b-nav-item to="/cook">주방</b-nav-item>
        <b-nav-item to="/oneday_need">일사용량 수정</b-nav-item>
        <b-nav-item to="/necessary_quantity">필요재고량 수정</b-nav-item>
      </b-navbar-nav>
    </b-collapse>
  </b-navbar>
</div>
</template>

<script>
export default {
    name: 'Header'
}
</script>