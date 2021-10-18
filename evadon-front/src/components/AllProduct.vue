<template>
    <div>
    <b-table striped dark hover :items="items" :fields="fields">
        <template #cell(mody_quantity)>
            <b-row>
                <b-col>
                    <b-button size="sm" class="mr-2" @click="addQuantity">
                        +
                    </b-button>
                </b-col>
               
            </b-row>
        </template>
        <template #cell(mody_quantity2)>
            <b-row>
                
                <b-col>
                    <b-button size="sm" class="mr-2" @click="popQuantity">
                        -
                    </b-button>
                </b-col>
                
            </b-row>
        </template>
        <template #cell(mody_quantity3)>
            <b-row>
                
                <b-col>
                    <b-button size="sm" class="mr-2" @click="mody_product">
                        수정
                    </b-button>
                </b-col>
                
            </b-row>
        </template>
    </b-table>   
    </div>
</template>

<script>
import {getAllProduct,popQuantity,addQuantity} from '../service'
export default {
    
    name: 'AllProduct',
    
    data(){
        return{
            fields:[
                {
                    key: 'id',
                    lavel: 'id'
                },
                {
                    key: 'name',
                    label: '품명'
                },
                {
                    key: 'orderQuantity',
                    label: '주문량'
                },
                {
                    key: 'quantity',
                    label: '현재고'
                },
                {
                    key: 'mody_quantity',
                    label: '재고+'
                },
                {
                    key: 'mody_quantity2',
                    label: '재고-'
                },
                {
                    key: 'mody_quantity3',
                    label: '수정'
                }
            ],
            items: [],
        }
    },
    async created(){
        const ret = await getAllProduct()
        this.items = ret.data
        console.log("created")
    },
    beforeCreate(){
        console.log("beforeCreate")
    },
    beforeMount(){
        console.log("beforeMount")
    },
    mounted(){
        console.log("mounted")
    },
    async beforeUpdate(){
        console.log("beforeUpdate")
    },
    updated(){
        console.log("updated")
    },
    beforeDestroy(){
        console.log("beforeDestroy")
    },
    destroyed(){
        console.log("destroyed")
    },
    methods: {
        popQuantity(event){ // 재고 1개 차감 버튼
            const items = this.items
            // 0번째 자식 : 즉 가장 왼쪽의 로우(현재 id값)
            const index = event.target.parentNode.parentNode.parentNode.parentNode.children[0].innerText
            // items는 배열이라 0번부터 시작 ( id값은 1번부터 시작)
            const selectedRow = items[index -1]
            // promise 객체로 받아옴
            const res = popQuantity(selectedRow)    
            res.then((res)=>{ // res.status == 200 인것만 처리함
                    if(res.data == "ok"){
                        // 4번째 컬럼 (현재는 현재고 컬럼)
                        const quan = event.target.parentNode.parentNode.parentNode.parentNode.children[3]
                        // 현재고 컬럼을 넘버로 변경 후 -1을 하고 다시 대입
                        quan.innerText = Number(quan.innerText) - 1
                    }else{
                        // 오류메시지 출력
                        alert(res.data)
                    }   
                }).catch(error=>{
                    console.log(error)
                })                      
        },
        addQuantity(event){ // 재고 1개 추가 버튼
            const items = this.items
            // 0번째 자식 : 즉 가장 왼쪽의 로우(현재 id값)
            const index = event.target.parentNode.parentNode.parentNode.parentNode.children[0].innerText
            // items는 배열이라 0번부터 시작 ( id값은 1번부터 시작)
            const selectedRow = items[index-1]
            // promise 객체로 받아옴
            const res = addQuantity(selectedRow)
            res.then((res)=>{ // res.status == 200 인것만 처리함
                if(res.data == "ok"){
                        // 4번째 컬럼 (현재는 현재고 컬럼)
                        const quan = event.target.parentNode.parentNode.parentNode.parentNode.children[3]
                        // 현재고 컬럼을 넘버로 변경 후 +1을 하고 다시 대입
                        quan.innerText = Number(quan.innerText) + 1
                }else{
                        // 오류메시지 출력
                        alert(res.data)
                    }   
                }).catch(error=>{
                    console.log(error)
                })                      
        },
        mody_product(item,index,event){
            this.$router.push({
                path : `/item/detail/${item.id}`,
                params: {a:'wow',b:'123'}
            })
        }
    }
    
}
</script>